package com.badmitry.vtbhackaton.navigation

import com.badmitry.domain.entities.AuthData
import com.badmitry.vtbhackaton.fragments.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreensProvider(private val screen: Screens, private val params: AuthData? = null, private val replace: Boolean = false) : SupportAppScreen() {
    override fun getFragment(): BaseFragment {
        if (replace) {
            return FragmentMain()
        } else {
            return when (screen) {
                Screens.SIGNING -> FragmentSigning()
                Screens.MAIN -> FragmentMain.createInstance(params)
                Screens.SELECT_PARTITION -> FragmentSelectPartition()
                Screens.APPLICATION_FIELD -> FragmentCreditFields()
            }
        }
    }
}