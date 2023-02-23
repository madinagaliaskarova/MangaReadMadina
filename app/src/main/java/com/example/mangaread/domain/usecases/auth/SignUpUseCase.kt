package com.example.mangaread.domain.usecases.auth

import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.domain.repository.AuthRepository

class SignUpUseCase constructor(
    private val authRepository: AuthRepository
) {
    fun signUp(signUpRequest: SignUpRequest) = authRepository.signUp(signUpRequest)
}