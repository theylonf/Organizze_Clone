package com.keneitec.theylonf.organizzeclone.view.intro

import com.google.firebase.auth.FirebaseUser
import com.keneitec.theylonf.organizzeclone.ViewModelBase
import com.keneitec.theylonf.organizzeclone.data.repository.FirebaseAuthRepository

class IntroViewModel : ViewModelBase() {

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuthRepository().getUser()
    }
}
