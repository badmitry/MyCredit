package com.badmitry.vtbhackaton.viewmodules

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.badmitry.domain.entities.EventArgs
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(app: Application) : AndroidViewModel(app) {

    private val compositeDisposables = CompositeDisposable()

    private val progressData by lazy {
        MutableLiveData<EventArgs<Boolean>>()
    }

    private val errorData by lazy {
        MutableLiveData<EventArgs<Throwable>>()
    }

    override fun onCleared() {
        compositeDisposables.clear()
        super.onCleared()
    }

    fun observe(
        owner: LifecycleOwner,
        onProgress: (EventArgs<Boolean>) -> Unit,
        onError: (EventArgs<Throwable>) -> Unit
    ) {
        errorData.observe(owner, Observer { onError(it!!) })
        progressData.observe(owner, Observer { onProgress(it!!) })
    }
}