package com.badmitry.vtbhackaton.fragments

import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentSelectPartitionBinding
import com.badmitry.vtbhackaton.viewmodules.FragmentSelectPartitionViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapType
import javax.inject.Inject
import android.location.Geocoder
import java.io.IOException
import java.util.*


class FragmentSelectPartition : BaseFragment() {
    private lateinit var binding: FragmentSelectPartitionBinding
    private lateinit var viewModel: FragmentSelectPartitionViewModel
    private val CAMERA_TARGET: Point = Point(59.952, 30.318)
    private val MAX_ZOOM = 30

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yandexMap.map.move(CameraPosition(CAMERA_TARGET, 15f, 0f, 0f))
        binding.yandexMap.map.setMapType(MapType.VECTOR_MAP)
//        binding.yandexMap.map.addTapListener {
//            val sldkfjkd = it.geoObject.attributionMap.keys.size
//            return@addTapListener sldkfjkd
//        }
    }

    private fun getCity(latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        var city: String? = null
        try {
            val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]
                city = returnedAddress.adminArea
            } else {
                city = "Error"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            city = "Error"
        }
        return city
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

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentSelectPartitionViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onDataChanged)
    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.app_name, false)
    }

    private fun onDataChanged(int: Int) {

    }
}