package com.hyungilee.mvvmarchitecturekotlinwithroom.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hyungilee.mvvmarchitecturekotlinwithroom.R
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.AppDatabase
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.MyApi
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.NetworkConnectionInterceptor
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.repositories.UserRepository
import com.hyungilee.mvvmarchitecturekotlinwithroom.databinding.ActivityLoginBinding
import com.hyungilee.mvvmarchitecturekotlinwithroom.ui.home.HomeActivity
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.hide
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.show
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

//        setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    //Main activity화면에서 로그인한 상태일때 back버튼을 누르게 되면,
                    //Login화면으로 돌아가서는 안된다. 이 처리를 여기서 해준다.
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
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
