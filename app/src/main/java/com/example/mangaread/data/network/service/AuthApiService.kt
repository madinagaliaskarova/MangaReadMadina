package com.example.mangaread.data.network.service

import com.example.mangaread.domain.models.LoginResponse
import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.domain.models.SignUpResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {

    @POST("api/auth/signin/")
    @FormUrlEncoded
   suspend fun signIn(
        @Field("username") username:String,
        @Field("password") password:String,
    ): Response<LoginResponse>

    @POST("api/auth/signup/")
   suspend fun signUp(
       @Body signUpRequest: SignUpRequest
    ): Response<SignUpResponse>


    @POST("api/auth/signup/")
    @Multipart
   suspend fun sendFile(@Part body: MultipartBody.Part)

}