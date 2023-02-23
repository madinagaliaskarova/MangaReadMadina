package com.example.mangaread.domain.repository

import com.example.mangaread.domain.models.LoginResponse
import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.domain.models.SignUpResponse
import com.example.mangaread.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    fun signIn(username: String, password: String): Flow<Resource<LoginResponse>>

    fun signUp(signUpRequest: SignUpRequest): Flow<Resource<SignUpResponse>>
}