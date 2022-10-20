package com.keneitec.theylonf.organizzeclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModelBase : ViewModel() {
    protected val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> = _loading

    protected fun setMsg(str: String) {
        _msg.value = str
    }

    fun setLoading(boolean: Boolean) {
        _loading.value = boolean
    }
}
