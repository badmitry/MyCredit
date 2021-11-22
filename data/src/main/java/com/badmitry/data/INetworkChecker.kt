package com.badmitry.data

import io.reactivex.Single

interface INetworkChecker {
    fun isConnect(): Single<Boolean>
}