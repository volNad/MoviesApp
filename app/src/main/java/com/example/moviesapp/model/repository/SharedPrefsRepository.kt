package com.example.moviesapp.model.repository

interface SharedPrefsRepository {
    fun saveInPrefs(value: String): String
}