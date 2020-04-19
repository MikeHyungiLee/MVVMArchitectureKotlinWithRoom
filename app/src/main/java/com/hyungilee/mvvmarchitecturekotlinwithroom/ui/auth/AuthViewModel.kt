package com.hyungilee.mvvmarchitecturekotlinwithroom.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.repositories.UserRepository
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.Coroutines

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    // login 버튼 event function
    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // fail
            authListener?.onFailure("Invalid email or password")
            return
        }
        // success
        // get loginResponse from UserRepository()
        // UserRepository()로 다른 클래스 안에서 다른 객체를 직접 생성하는 것은 좋지 않은 방법이다. (Tight coupling)

        // Retrofit call 함수를 이용해서 작성했을 당시의 코드
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListener?.onSuccess(loginResponse)

        // util > Coroutines 에 작성해 준 main() 메소드를 사용해서 실행해준다.
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error code: ${response.code()}")
            }
        }


    }

}