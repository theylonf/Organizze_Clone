package com.keneitec.theylonf.organizzeclone.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.keneitec.theylonf.organizzeclone.ViewModelBase
import com.keneitec.theylonf.organizzeclone.data.Result
import com.keneitec.theylonf.organizzeclone.data.repository.FirebaseAuthRepository
import com.keneitec.theylonf.organizzeclone.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModelBase() {

    private val authRepository = FirebaseAuthRepository()

    private val _user = MutableLiveData<Result<FirebaseUser>>()
    val user: LiveData<Result<FirebaseUser>> = _user

    fun createAccount(user: User) {
        viewModelScope.launch {
            setLoading(true)
            withContext(Dispatchers.IO) {
                authRepository.createUser(user) { result ->
                    when (result) {
                        is Result.Success -> {
                            setLoading(false)
                            setMsg("Sucesso ao cadastrar usuario")
                            _user.value = result
                        }

                        is Result.Error -> {
                            setLoading(false)
                            setMsg(result.msg.toString())
                        }
                        Result.Loading -> {}
                    }
                }
            }
        }
    }
}
