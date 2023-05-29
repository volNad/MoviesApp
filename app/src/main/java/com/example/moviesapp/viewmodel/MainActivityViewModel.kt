package com.example.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.User
import com.example.moviesapp.model.repository.FirebaseRepository
import com.example.moviesapp.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel : ViewModel() {
    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()
    
    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}