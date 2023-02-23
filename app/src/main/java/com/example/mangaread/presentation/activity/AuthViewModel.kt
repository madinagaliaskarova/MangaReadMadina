package com.example.mangaread.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mangaread.data.network.service.AuthApiService
import com.example.mangaread.domain.models.LoginResponse
import com.example.mangaread.domain.models.SignUpRequest
import com.example.mangaread.domain.models.SignUpResponse
import com.example.mangaread.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import okio.IOException

class AuthViewModel(private val authApiService: AuthApiService):BaseViewModel() {

    fun signIn(username:String,password:String): LiveData<LoginResponse> {
        val data = MutableLiveData<LoginResponse>()
        viewModelScope.launch {
            try {
                val result = authApiService.signIn(username, password)

                if (result.isSuccessful){
                    data.postValue(result.body())
                }else{
                    data.postValue(LoginResponse("denied","",0,""))
                }

            }catch (e:IOException){
                e.printStackTrace()
            }
        }
        return data
    }

    fun signUp(signUpRequest: SignUpRequest): LiveData<SignUpResponse> {
        val data = MutableLiveData<SignUpResponse>()
        viewModelScope.launch {
            val result = authApiService.signUp(signUpRequest)
            if (result.isSuccessful){
                data.postValue(result.body())
            }else{
                data.postValue(SignUpResponse(0,"","",""))
            }
        }
        return data
    }

}