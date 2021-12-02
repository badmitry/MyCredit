package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.badmitry.domain.entities.Bbox
import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import com.badmitry.domain.interactors.YandexPartitionsInteractor
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import com.yandex.mapkit.map.CameraPosition
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentSelectPartitionViewModel @Inject constructor(
    private val router: Router,
    private val yandexPartitionsInteractor: YandexPartitionsInteractor,
    app: Application
) : BaseViewModel(app) {

    fun replaceFragment(screen: Screens) {
        router.replaceScreen(FragmentScreensProvider(screen))
    }

    fun requestPartitions(bbox: Bbox) {
        val params = YandexPartitionsInteractor.Params(bbox)
        yandexPartitionsInteractor(params, ::onSubscribe, ::onFinally, ::onGetPartitions, ::onError)
    }

    private fun onGetPartitions(partitions: YandexResponse) {
        yandexResponseLiveData.value = partitions
    }

    fun saveCurrentPosition(cameraPosition: CameraPosition) {
        currentPositionLiveData.value = cameraPosition
    }

    fun saveCurrentLocation(location: Location) {
        currentLocationLiveData.value = location
    }

    val yandexResponseLiveData = MutableLiveData<YandexResponse>()
    val currentPositionLiveData = MutableLiveData<CameraPosition>()
    val currentLocationLiveData = MutableLiveData<Location>()
}