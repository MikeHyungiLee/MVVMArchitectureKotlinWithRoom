package com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.response

import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User


data class AuthResponse (
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)