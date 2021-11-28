package com.badmitry.vtbhackaton.navigation

import com.badmitry.vtbhackaton.fragments.BaseFragment
import com.badmitry.vtbhackaton.fragments.FragmentSelectPartition
import com.badmitry.vtbhackaton.fragments.FragmentSigning
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreensProvider(private val screen: Screens) : SupportAppScreen() {
    override fun getFragment(): BaseFragment {
        return when (screen) {
            Screens.SIGNING -> FragmentSigning()
            Screens.SELECT_PARTITION -> FragmentSelectPartition()
        }
    }
}