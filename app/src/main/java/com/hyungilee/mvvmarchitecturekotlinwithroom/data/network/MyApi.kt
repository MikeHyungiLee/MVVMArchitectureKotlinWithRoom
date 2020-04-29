package com.hyungilee.mvvmarchitecturekotlinwithroom.data.network

import android.telecom.Call
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.network.response.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface MyApi {

    // POST 방식으로 Retrofit 을 사용할때는 FormUrlEncoded annotation 을 해줘야 한다.
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        // Field 변수(Api "Key"value)와 match 되어야 한다. 따라서 @Field annotation 처리를 해주어야 한다.
        @Field("email")email: String,
        @Field("password")password: String
    // Retrofit 2 (api 쪽의 ResponseBody 실행)
    ) : Response<AuthResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{

            // MyApi인터페이스에서는 context를 지정할 수 없다.
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}