package com.badmitry.vtbhackaton.navigation

import com.badmitry.domain.entities.FragmentData
import com.badmitry.vtbhackaton.fragments.BaseFragment
import com.badmitry.vtbhackaton.fragments.FragmentMain
import com.badmitry.vtbhackaton.fragments.FragmentSelectPartition
import com.badmitry.vtbhackaton.fragments.FragmentSigning
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreensProvider(private val screen: Screens, private val params: FragmentData<Any>? = null) : SupportAppScreen() {
    override fun getFragment(): BaseFragment {
        return when (screen) {
            Screens.SIGNING -> FragmentSigning()
            Screens.MAIN -> FragmentMain.createInstance(params)
            Screens.SELECT_PARTITION -> FragmentSelectPartition()
        }
    }
}