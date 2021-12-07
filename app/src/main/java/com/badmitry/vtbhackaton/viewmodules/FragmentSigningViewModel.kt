package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.FragmentData
import com.badmitry.domain.entities.User
import com.badmitry.domain.interactors.VTBAuthInteractor
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentSigningViewModel @Inject constructor(
    private val router: Router,
    private val authInteractor: VTBAuthInteractor,
    app: Application
) : BaseViewModel(app) {

    private val host = "hackaton.bankingapi.ru"

    private fun replaceFragment(screen: Screens, params: FragmentData<Any>) {
        router.replaceScreen(FragmentScreensProvider(screen, params))
    }

    fun requestAuth(clientId: String, clientSecret: String) {
        val authCredentials = AuthCredentials("client_credentials", clientId, clientSecret, host)
        val params = VTBAuthInteractor.Params(authCredentials)
        authInteractor(params, ::onSubscribe, ::onFinally, ::onAuth, ::onError)
    }

    private fun onAuth(user: User) {
        replaceFragment(Screens.MAIN, FragmentData(user))
    }

    val liveData = MutableLiveData<Int>()
}