package com.badmitry.vtbhackaton.view

import com.badmitry.domain.entities.EventArgs

interface OnProgressView {
    fun onProgress(arg: EventArgs<Boolean>)
}