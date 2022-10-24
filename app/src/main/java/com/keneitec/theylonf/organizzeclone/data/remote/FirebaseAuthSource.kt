package com.keneitec.theylonf.organizzeclone.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.keneitec.theylonf.organizzeclone.model.User

class FirebaseAuthSource {
    companion object {
        var auth: FirebaseAuth? = null

        fun getFirebaseAuth(): FirebaseAuth {
            if (auth == null) {
                auth = FirebaseAuth.getInstance()
            }
            return auth as FirebaseAuth
        }

        fun getFirebaseUser(): FirebaseUser? {
            return getFirebaseAuth().currentUser
        }

        fun loginWithEmailAndPassword(user: User): Task<AuthResult> {
            return getFirebaseAuth().signInWithEmailAndPassword(user.email, user.password)
        }

        fun createUser(user: User): Task<AuthResult> {
            return getFirebaseAuth().createUserWithEmailAndPassword(
                user.email,
                user.password
            )
        }

        fun deleteUser(): Task<Void>? {
            return getFirebaseAuth().currentUser?.delete()
        }
    }
}
