package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
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

    private val appClientId = "920cb60cce58e560c4463b0c2e2a9970"
    private val appClientSecret = "157baf586bde3eb6795dd15727107986"
    private val redirectUri = "https://badmitry.com"
    private val host = "hackaton.bankingapi.ru"

    private val login = "team21@app.hackaton.bankingapi.ru"
    private val password = "jGW2R1Gi"

    private val login1 = "team1@app.hackaton.bankingapi.ru"
    private val password1 = "BTLCRBLs"

    fun replaceFragment(screen: Screens) {
        router.replaceScreen(FragmentScreensProvider(screen))
    }

    fun requestAuth(clientId: String, clientSecret: String) {
        val authCredentials = AuthCredentials("client_credentials", clientId, clientSecret, host)
        val params = VTBAuthInteractor.Params(authCredentials)
        authInteractor(params, ::onSubscribe, ::onFinally, ::onAuth, ::onError)
    }

    private fun onAuth(authToken: AuthToken) {
        replaceFragment(Screens.SELECT_PARTITION)
    }

    val liveData = MutableLiveData<Int>()
}