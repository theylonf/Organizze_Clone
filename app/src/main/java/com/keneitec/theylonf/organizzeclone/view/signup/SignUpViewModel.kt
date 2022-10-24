package com.keneitec.theylonf.organizzeclone.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keneitec.theylonf.organizzeclone.ViewModelBase
import com.keneitec.theylonf.organizzeclone.data.ResultFirebase
import com.keneitec.theylonf.organizzeclone.data.repository.FirebaseAuthRepository
import com.keneitec.theylonf.organizzeclone.data.repository.FirebaseDatabaseRepository
import com.keneitec.theylonf.organizzeclone.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModelBase() {

    private val authRepository = FirebaseAuthRepository()
    private val databaseRepository = FirebaseDatabaseRepository()

    private val _user = MutableLiveData<ResultFirebase<Void?>>()
    val user: LiveData<ResultFirebase<Void?>> = _user

    fun createAccount(user: User) {
        viewModelScope.launch {
            setLoading(true)
            withContext(Dispatchers.IO) {
                authRepository.createUser(user) { result ->
                    when (result) {
                        is ResultFirebase.Success -> {
                            saveUserInDb(user)
                        }

                        is ResultFirebase.Error -> {
                            setLoading(false)
                            setMsg(result.msg.toString())
                        }
                        ResultFirebase.Loading -> {}
                    }
                }
            }
        }
    }

    private fun saveUserInDb(user: User) {
        databaseRepository.saveUser(user) { result ->
            when (result) {
                is ResultFirebase.Success -> {
                    _user.value = result
                    setLoading(false)
                    setMsg("Sucesso ao cadastrar usuario")
                }

                is ResultFirebase.Error -> {
                    deleteUser()
                    Log.d("TAG", "saveUserInDb: ${result.msg}")
                }
                is ResultFirebase.Loading -> {}
            }
        }
    }

    private fun deleteUser() {
        authRepository.deleteUser { result ->
            when (result) {
                is ResultFirebase.Success -> {
                    setLoading(false)
                    setMsg("Erro ao cadastrar usuario")
                }

                is ResultFirebase.Error -> {
                    setLoading(false)
                    setMsg(result.msg.toString())
                }
                is ResultFirebase.Loading -> {}
                null -> TODO()
            }
        }
    }
}
