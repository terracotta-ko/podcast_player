package com.kobe.common.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CommonCoroutinesDispatcher {

    val threadIO: CoroutineContext

    val threadUI: CoroutineContext
}

class CommonCoroutinesDispatcherDefault : CommonCoroutinesDispatcher {

    override val threadIO: CoroutineContext
        get() = Dispatchers.IO

    override val threadUI: CoroutineContext
        get() = Dispatchers.Main
}

class CommonCoroutinesDispatcherUnconfined : CommonCoroutinesDispatcher {

    override val threadIO: CoroutineContext
        get() = Dispatchers.Unconfined

    override val threadUI: CoroutineContext
        get() = Dispatchers.Unconfined
}
