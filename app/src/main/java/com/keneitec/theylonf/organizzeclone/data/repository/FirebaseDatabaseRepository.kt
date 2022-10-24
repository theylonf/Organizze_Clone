package com.keneitec.theylonf.organizzeclone.data.repository

import com.keneitec.theylonf.organizzeclone.data.ResultFirebase
import com.keneitec.theylonf.organizzeclone.data.remote.FirebaseDatabaseSource
import com.keneitec.theylonf.organizzeclone.model.User

class FirebaseDatabaseRepository {

    private val database = FirebaseDatabaseSource

    fun saveUser(user: User, b: ((ResultFirebase<Void?>) -> Unit)) {
        b.invoke(ResultFirebase.Loading)
        database.saveUser(user)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    b.invoke(ResultFirebase.Success(it.result))
                } else b.invoke(ResultFirebase.Error(it.exception?.message))
            }?.addOnFailureListener {
                b.invoke(ResultFirebase.Error(it.message))
            }
    }
}
