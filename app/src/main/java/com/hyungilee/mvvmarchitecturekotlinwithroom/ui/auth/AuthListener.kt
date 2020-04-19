package com.hyungilee.mvvmarchitecturekotlinwithroom.ui.auth

import androidx.lifecycle.LiveData
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User

interface AuthListener {
    // operation is started (progress bar)
    fun onStarted()
    // authentication success
    fun onSuccess(user: User)
    // authentication fail
    fun onFailure(message: String)
}