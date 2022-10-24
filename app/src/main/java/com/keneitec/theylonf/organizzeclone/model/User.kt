package com.keneitec.theylonf.organizzeclone.model

import com.google.firebase.database.Exclude

data class User(
    @Exclude @get:Exclude
    val idUser: String? = null,
    var name: String? = "",
    var email: String,
    @Exclude @get:Exclude @set:Exclude
    var password: String
)
