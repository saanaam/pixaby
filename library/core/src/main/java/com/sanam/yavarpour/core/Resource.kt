package com.sanam.yavarpour.core

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error(val error: Throwable?) : Resource<Nothing>()
    val isFail get() = this is Error
    val exception get() =(this as? Error)?.error
    val valueOrNull get() = (this as? Success)?.value
}