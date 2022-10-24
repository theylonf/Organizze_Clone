package com.keneitec.theylonf.organizzeclone.data.repository

import com.google.firebase.auth.FirebaseUser
import com.keneitec.theylonf.organizzeclone.data.ResultFirebase
import com.keneitec.theylonf.organizzeclone.data.remote.FirebaseAuthSource
import com.keneitec.theylonf.organizzeclone.model.User

class FirebaseAuthRepository {
    private val firebaseAuthService = FirebaseAuthSource

    suspend fun loginUser(user: User, b: ((ResultFirebase<FirebaseUser>) -> Unit)) {
        b.invoke(ResultFirebase.Loading)
        firebaseAuthService.loginWithEmailAndPassword(user).addOnCompleteListener {
            b.invoke(ResultFirebase.Success(it.result.user))
        }.addOnFailureListener {
            b.invoke(ResultFirebase.Error(msg = it.message))
        }
    }

    fun createUser(user: User, b: ((ResultFirebase<FirebaseUser>) -> Unit)) {
        b.invoke(ResultFirebase.Loading)
        firebaseAuthService.createUser(user).addOnSuccessListener {
            b.invoke(ResultFirebase.Success(it.user))
        }.addOnFailureListener {
            b.invoke(ResultFirebase.Error(msg = it.message))
        }
    }

    fun deleteUser(b: ((ResultFirebase<Void>?) -> Unit)) {
        b.invoke(ResultFirebase.Loading)
        firebaseAuthService.deleteUser()?.addOnCompleteListener {
            b.invoke(ResultFirebase.Success(it.result))
        }?.addOnFailureListener {
            b.invoke(ResultFirebase.Error(it.message))
        }
    }

    fun getUser(): FirebaseUser? {
        return firebaseAuthService.getFirebaseUser()
    }
}
