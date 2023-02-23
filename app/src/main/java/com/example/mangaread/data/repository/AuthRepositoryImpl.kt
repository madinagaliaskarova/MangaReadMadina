package com.example.mangaread.data.repository

import com.example.mangaread.data.base.BaseRepository
import com.example.mangaread.data.network.service.AuthApiService
import com.example.mangaread.domain.models.LoginResponse
import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.domain.models.SignUpResponse
import com.example.mangaread.domain.repository.AuthRepository
import com.example.mangaread.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class AuthRepositoryImpl(
    private val authApiService: AuthApiService
):AuthRepository,BaseRepository() {

    override fun signIn(username:String,password:String): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = authApiService.signIn(username, password).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun signUp(signUpRequest: SignUpRequest): Flow<Resource<SignUpResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = authApiService.signUp(signUpRequest).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }
}
