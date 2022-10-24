package com.keneitec.theylonf.organizzeclone.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.keneitec.theylonf.organizzeclone.model.User

class FirebaseDatabaseSource {
    companion object {
        var dataBase: DatabaseReference? = null

        fun getFirebaseDatabase(): DatabaseReference {
            if (dataBase == null) {
                dataBase = FirebaseDatabase.getInstance().reference
            }
            return dataBase as DatabaseReference
        }

        fun saveUser(user: User): Task<Void>? {
            return dataBase?.child(USERS)
                ?.child(user.idUser!!)
                ?.setValue(user)
        }

        private const val USERS = "usuarios"
    }
}
