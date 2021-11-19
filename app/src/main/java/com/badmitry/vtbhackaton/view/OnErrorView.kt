package com.badmitry.vtbhackaton.view

import com.badmitry.domain.entities.EventArgs

interface OnErrorView {
    fun onError(arg: EventArgs<Throwable>)
}