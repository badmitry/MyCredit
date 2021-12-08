package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.data.DataSaver
import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.entities.User
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentMainViewModel @Inject constructor(
    private val router: Router,
//    private val authInteractor: VTBAuthInteractor,
    app: Application
) : BaseViewModel(app) {

    fun navigateFragment(screen: Screens) {
        router.navigateTo(FragmentScreensProvider(screen))
    }

//    fun requestAuth(clientId: String, clientSecret: String) {
//        val authCredentials = AuthCredentials("client_credentials", clientId, clientSecret, host)
//        val params = VTBAuthInteractor.Params(authCredentials)
//        authInteractor(params, ::onSubscribe, ::onFinally, ::onAuth, ::onError)
//    }

    val liveData = MutableLiveData<AuthData>()

    fun setAuthData(authData: AuthData) {
        DataSaver.instance.authData = authData
        liveData.value = authData
    }
}