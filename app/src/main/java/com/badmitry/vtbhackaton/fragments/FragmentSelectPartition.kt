package com.badmitry.vtbhackaton.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.domain.entities.Bbox
import com.badmitry.domain.entities.yandexpartitions.Partitions
import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentSelectPartitionBinding
import com.badmitry.vtbhackaton.viewmodules.FragmentSelectPartitionViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.runtime.image.ImageProvider
import javax.inject.Inject


class FragmentSelectPartition : BaseFragment(), MapObjectTapListener {
    private lateinit var binding: FragmentSelectPartitionBinding
    private lateinit var viewModel: FragmentSelectPartitionViewModel
    private val CAMERA_TARGET: Point = Point(55.751, 37.619)
    private val MIN_ZOOM = 10F
    private val handler = Handler()
    private val BANK_CATEGORIES = "banks"
    private var currentCameraPosition: CameraPosition? = null
    private var userLocation: Location? = null

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectPartitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val cameraListener = CameraListener { map, cameraPosition, cameraUpdateReason, b ->
        viewModel.saveCurrentPosition(cameraPosition)
        if (cameraPosition.zoom < MIN_ZOOM) {
            binding.yandexMap.map.move(
                CameraPosition(
                    cameraPosition.target,
                    MIN_ZOOM,
                    cameraPosition.azimuth,
                    cameraPosition.tilt
                )
            )
        }
        handler.removeCallbacks(requestPartitionRunnable)
        handler.postDelayed(requestPartitionRunnable, 1000)
    }

    private val requestPartitionRunnable = Runnable {
        kotlin.run {
            val region = binding.yandexMap.map.visibleRegion
            val bbox = Bbox(
                region.bottomLeft.longitude.toString(),
                region.bottomLeft.latitude.toString(),
                region.topRight.longitude.toString(),
                region.topRight.latitude.toString()
            )
            Log.e(
                "!!!",
                bbox.getBbox()
            )
            viewModel.requestPartitions(bbox)
        }
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
        initComponent()
    }

    private fun initComponent() {
        binding.layoutPartition.btnClose.setOnClickListener{
            binding.layoutPartition.nsvContainer.visibility = View.GONE
        }
        binding.btnNavigation.setOnClickListener {
            initMap()
        }
        binding.btnMinus.setOnClickListener {
            initMap(-1)
        }
        binding.btnPlus.setOnClickListener {
            initMap(1)
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.yandexMap.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        binding.yandexMap.onStop()
    }

    private fun initMap(zoomChange: Int? = null) {
        currentCameraPosition?.let {
            zoomChange?.let { zoom ->
                binding.yandexMap.map.move(
                    CameraPosition(
                        it.target,
                        it.zoom + zoom,
                        it.azimuth,
                        it.tilt
                    )
                )
            } ?: binding.yandexMap.map.move(CameraPosition(it.target, it.zoom, 0f, it.tilt))
        } ?: run {
            val locationManager: LocationManager? =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            val location = locationManager?.let { getLastBestLocation(it) }
            location?.let {
                viewModel.saveCurrentLocation(location)
                binding.yandexMap.map.move(
                    CameraPosition(
                        Point(location.latitude, location.longitude),
                        15f,
                        0f,
                        0f
                    )
                )
            } ?: binding.yandexMap.map.move(CameraPosition(CAMERA_TARGET, 15f, 0f, 0f))
        }
        binding.yandexMap.map.setMapType(MapType.VECTOR_MAP)
        binding.yandexMap.map.addCameraListener(cameraListener)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentSelectPartitionViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.yandexResponseLiveData.observe(this, ::onPartitionsDownloaded)
        viewModel.currentPositionLiveData.observe(this, ::onCurrentPositionChanged)
        viewModel.currentLocationLiveData.observe(this, ::onCurrentLocationChanged)
    }

    private fun onCurrentLocationChanged(location: Location?) {
        Log.e("!!!", "onCurrentLocationChanged $location")
        userLocation = location
        drawUserLocationMark()
    }

    private fun drawUserLocationMark() {
        userLocation?.let {
            val point = Point(it.latitude, it.longitude)
            val pointCollection = binding.yandexMap.map.mapObjects.addCollection()
            pointCollection.addPlacemark(
                point,
                ImageProvider.fromResource(requireContext(), R.drawable.m_i_point)
            )
        }
    }

    private fun onCurrentPositionChanged(cameraPosition: CameraPosition) {
        currentCameraPosition = cameraPosition
    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.app_name, false)
    }

    private fun onPartitionsDownloaded(response: YandexResponse) {
//        binding.yandexMap.map.mapObjects.clear()
        val pointCollection = binding.yandexMap.map.mapObjects.addCollection()
        pointCollection.addTapListener(this)
        Log.e(
            "!!!",
            "${response.features.size}"
        )
        drawUserLocationMark()
        response.features.forEach {
            if (it.properties.companyMetaData.categories[0].clas == BANK_CATEGORIES) {
                val point = Point(
                    it.geometry.coordinates[1].toDouble(),
                    it.geometry.coordinates[0].toDouble()
                )
                val placemark = pointCollection.addPlacemark(
                    point,
                    ImageProvider.fromResource(requireContext(), R.drawable.vtbmark)
                )
                placemark.userData = it
            }
        }
    }

    private fun getLastBestLocation(mLocationManager: LocationManager): Location? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && requireActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                return null
            }
        }
        val locationGPS: Location? =
            mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val locationNet: Location? =
            mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        var GPSLocationTime: Long = 0
        if (null != locationGPS) {
            GPSLocationTime = locationGPS.getTime()
        }
        var NetLocationTime: Long = 0
        if (null != locationNet) {
            NetLocationTime = locationNet.getTime()
        }
        return if (0 < GPSLocationTime - NetLocationTime) {
            locationGPS
        } else {
            locationNet
        }
    }

    override fun onMapObjectTap(p0: MapObject, p1: Point): Boolean {
        Log.e(
            "!!!",
            "onMapObjectTap: ${(p0.userData as Partitions).properties.companyMetaData.hourse.text}"
        )
        (p0.userData as Partitions).properties.let{
            binding.layoutPartition.tvAddress.text = it.companyMetaData.address
            binding.layoutPartition.tvHours.text = it.companyMetaData.hourse.text
            binding.layoutPartition.nsvContainer.visibility = View.VISIBLE
        }
        return true
    }
}