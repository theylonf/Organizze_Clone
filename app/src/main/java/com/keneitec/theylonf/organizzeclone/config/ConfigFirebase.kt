package com.keneitec.theylonf.organizzeclone.config

import com.google.firebase.auth.FirebaseAuth

class ConfigFirebase {
    companion object {
        private lateinit var firebaseAuth: FirebaseAuth

        fun getFirebaseAuth(): FirebaseAuth {
            if (firebaseAuth == null) {
                firebaseAuth = FirebaseAuth.getInstance()
            }
            return firebaseAuth
        }
    }
}
