package com.example.moviesapp.model.repository

import com.example.moviesapp.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}