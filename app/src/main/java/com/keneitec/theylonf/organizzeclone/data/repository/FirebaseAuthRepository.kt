package com.keneitec.theylonf.organizzeclone.data.repository

import com.google.firebase.auth.FirebaseUser
import com.keneitec.theylonf.organizzeclone.data.Result
import com.keneitec.theylonf.organizzeclone.data.remote.FirebaseAuthSource
import com.keneitec.theylonf.organizzeclone.model.User

class FirebaseAuthRepository {
    private val firebaseAuthService = FirebaseAuthSource

    suspend fun loginUser(user: User, b: ((Result<FirebaseUser>) -> Unit)){
        b.invoke(Result.Loading)
        firebaseAuthService.loginWithEmailAndPassword(user).addOnCompleteListener {
            b.invoke(Result.Success(it.result.user))
        }.addOnFailureListener {
            b.invoke(Result.Error(msg = it.message))
        }
    }

    fun createUser(user: User, b: ((Result<FirebaseUser>) -> Unit)) {
        b.invoke(Result.Loading)
        firebaseAuthService.createUser(user).addOnSuccessListener {
            b.invoke(Result.Success(it.user))
        }.addOnFailureListener {
            b.invoke(Result.Error(msg = it.message))
        }
    }

    fun getUser(): FirebaseUser? {
        return firebaseAuthService.getFirebaseUser()
    }
}
