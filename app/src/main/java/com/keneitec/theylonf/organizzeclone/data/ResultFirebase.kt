package com.keneitec.theylonf.organizzeclone.data

sealed class ResultFirebase<out R> {
    data class Success<out T>(val data: T? = null, val msg: String? = null) : ResultFirebase<T>()
    class Error(val msg: String? = null) : ResultFirebase<Nothing>()
    object Loading : ResultFirebase<Nothing>()
}
