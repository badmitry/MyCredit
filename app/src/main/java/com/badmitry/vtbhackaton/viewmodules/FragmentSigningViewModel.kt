package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentSigningViewModel @Inject constructor(
    private val router: Router,
    app: Application
) : BaseViewModel(app) {

    fun replaceFragment(screen: Screens) {
        router.replaceScreen(FragmentScreensProvider(screen))
    }

    val liveData = MutableLiveData<Int>()
}