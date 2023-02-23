package com.example.mangaread.domain.usecases.auth

import com.example.mangaread.domain.repository.AuthRepository

class SignInUseCase constructor(
    private val authRepository: AuthRepository
) {
    fun signIn(username:String,password:String) = authRepository.signIn(username, password)
}