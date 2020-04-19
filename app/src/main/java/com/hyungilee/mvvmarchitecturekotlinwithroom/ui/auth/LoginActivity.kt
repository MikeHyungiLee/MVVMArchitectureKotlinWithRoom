package com.hyungilee.mvvmarchitecturekotlinwithroom.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hyungilee.mvvmarchitecturekotlinwithroom.R
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User
import com.hyungilee.mvvmarchitecturekotlinwithroom.databinding.ActivityLoginBinding
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.hide
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.show
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
//        toast("Login Started")
        progress_bar.show()
    }

    // 수정전 소스코드
//    override fun onSuccess(loginResponse: LiveData<String>) {
//        //toast("Login Success")
//        loginResponse.observe(this, Observer {
//            progress_bar.hide()
//            toast(it)
//        })
//    }

    // 수정후 소스코드
    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }


}
