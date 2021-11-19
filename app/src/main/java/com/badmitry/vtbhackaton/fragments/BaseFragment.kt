package com.badmitry.vtbhackaton.fragments

import android.os.Bundle
import android.view.View
import com.badmitry.domain.entities.EventArgs
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.view.OnErrorView
import com.badmitry.vtbhackaton.view.OnProgressView
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), OnErrorView, OnProgressView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    override fun onError(arg: EventArgs<Throwable>) {
        (requireActivity() as MainActivity).onError(arg)
    }

    override fun onProgress(arg: EventArgs<Boolean>) {
        (requireActivity() as MainActivity).onProgress(arg)
    }

    abstract fun setToolbar()
}