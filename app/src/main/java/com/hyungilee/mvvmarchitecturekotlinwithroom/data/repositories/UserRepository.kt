package com.hyungilee.mvvmarchitecturekotlinwithroom.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.MyApi
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.SafeApiRequest
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.response.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    // Api 메소드를 suspend fun 메소드로 수정한 뒤에 Repository method
    suspend fun userLogin(email: String, password: String) : AuthResponse{
        return apiRequest{ MyApi().userLogin(email, password) }
    }

    // Retrofit call 로 작성했을때의 코드
//    fun userLogin(email: String, password: String) : LiveData<String>{
//        val loginResponse = MutableLiveData<String>()
//
//        // 아래와 같이 해주기 보다는 Repository 에서 Api instance 를 초기화 시켜주고 사용하는 것이 좋다.
//        // 이 부분은 차후에 수정해보기
//        MyApi().userLogin(email, password)
//            .enqueue(object: Callback<ResponseBody>{
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    loginResponse.value = t.message
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if(response.isSuccessful){
//                        loginResponse.value = response.body()?.string()
//                    }else{
//                        loginResponse.value = response.errorBody()?.string()
//                    }
//                }
//            })
//        return loginResponse
//    }
}