package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.badmitry.data.DataSaver
import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.repositories.ISaverRepositories
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class FragmentMainViewModel @Inject constructor(
    private val router: Router,
    private val dbRepositories: ISaverRepositories,
    @param:Named("IoScheduler") private val scheduler: Scheduler,
    @param:Named("MainScheduler") private val postScheduler: Scheduler,
    app: Application
) : BaseViewModel(app) {

    fun navigateFragment(screen: Screens) {
        router.navigateTo(FragmentScreensProvider(screen))
    }

    val liveData = MutableLiveData<AuthData>()
    var applicationIdLiveData = MutableLiveData<List<VtbApplicationId>>()

    fun getApplicationId() {
        dbRepositories.getApplicationId().subscribeOn(scheduler).observeOn(postScheduler)
            .subscribe { it ->
                onSuccess(it)
            }
    }

    private fun onSuccess(list: List<VtbApplicationId>) {
        Log.e("!!!", "onSuccess: ${list.size}")
        applicationIdLiveData.value = list
    }

    fun setAuthData(authData: AuthData) {
        DataSaver.instance.authData = authData
        liveData.value = authData
    }
}