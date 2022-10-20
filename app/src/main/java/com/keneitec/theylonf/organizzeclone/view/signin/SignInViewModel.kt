package com.keneitec.theylonf.organizzeclone.view.signin

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

class SignInViewModel : ViewModelBase() {

    private val _firebaseUserResult = MutableLiveData<Result<FirebaseUser>>()
    val firebaseUserResult: LiveData<Result<FirebaseUser>> = _firebaseUserResult

    fun signInUser(user: User) {
        viewModelScope.launch {
            setLoading(true)
            withContext(Dispatchers.IO) {
                FirebaseAuthRepository().loginUser(user) { result: Result<FirebaseUser> ->
                    when (result) {
                        is Result.Success -> {
                            _firebaseUserResult.value = result
                            setLoading(false)
                            setMsg("Sucesso ao logar")
                        }
                        is Result.Error -> {
                            setMsg(result.msg.toString())
                            setLoading(false)
                        }
                        Result.Loading -> {}
                    }
                }
            }
        }
    }
}
