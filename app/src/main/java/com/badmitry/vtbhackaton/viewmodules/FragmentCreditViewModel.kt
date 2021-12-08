package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.badmitry.data.DataSaver
import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.entities.User
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.interactors.VTBAuthInteractor
import com.badmitry.domain.interactors.VTBCreditInteractor
import com.badmitry.vtbhackaton.fragments.FragmentMain
import com.badmitry.vtbhackaton.navigation.FragmentScreensProvider
import com.badmitry.vtbhackaton.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentCreditViewModel @Inject constructor(
    private val router: Router,
    private val creditInteractor: VTBCreditInteractor,
    app: Application
) : BaseViewModel(app) {

    private val host = "hackaton.bankingapi.ru"

    private fun backToMainFragment() {
        DataSaver.instance.authData?.let{
            router.backTo(null)
        }
    }

    private fun onAuth(id: VtbApplicationId) {
        DataSaver.instance
        backToMainFragment()
    }

    fun creditApplication() {
        DataSaver.instance.authData?.let{
            val params = VTBCreditInteractor.Params(it)
            creditInteractor(params, ::onSubscribe, ::onFinally, ::onAuth, ::onError)
        }
    }

    val liveData = MutableLiveData<Int>()
}