package com.hyungilee.mvvmarchitecturekotlinwithroom.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.repositories.UserRepository
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.ApiException
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.Coroutines
import com.hyungilee.mvvmarchitecturekotlinwithroom.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    // To observe the local db changes(User changes)
    fun getLoggedInUser() = repository.getUser()

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

//            val response = UserRepository().userLogin(email!!, password!!)
//            if(response.isSuccessful){
//                authListener?.onSuccess(response.body()?.user!!)
//            }else{
//                authListener?.onFailure("Error code: ${response.code()}")
//            }

        // util > Coroutines 에 작성해 준 main() 메소드를 사용해서 실행해준다.
        Coroutines.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let{
                    authListener?.onSuccess(it)
                    // local db에 User정보를 저장한다.
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }

}