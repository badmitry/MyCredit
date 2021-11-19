package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router,
    private val navigatorHolder: NavigatorHolder,
    app: Application
) : BaseViewModel(app) {
    fun setNavigator(navigator: SupportAppNavigator) {
        navigatorHolder.setNavigator(navigator)
    }

    fun replaceFragment(screen: Screens) {
        router.replaceScreen(FragmentScreensProvider(screen))
    }

    fun navigateTo(screen: Screens) {
        router.navigateTo(FragmentScreensProvider(screen))
    }

    override fun onCleared() {
        super.onCleared()
        navigatorHolder.removeNavigator()
    }

    val liveData = MutableLiveData<Int>()
}