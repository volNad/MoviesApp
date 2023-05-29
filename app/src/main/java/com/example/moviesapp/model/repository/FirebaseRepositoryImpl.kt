package com.example.moviesapp.model.repository

import com.example.moviesapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {

    private var database: DatabaseReference =
        Firebase.database("https://moviesapp-7e597-default-rtdb.europe-west1.firebasedatabase.app/").reference

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser)
    }
}