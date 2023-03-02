package com.sanam.yavarpour.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(delayMs: Long = 300L,
                 coroutineScope: CoroutineScope,
                 f: suspend (T) -> Unit): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob?.isCompleted != false) {
            debounceJob = coroutineScope.launch {
                delay(delayMs)
                f(param)
            }
        }
    }
}