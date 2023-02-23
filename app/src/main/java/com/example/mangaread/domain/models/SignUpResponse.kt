package com.example.mangaread.domain.models

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("username")
    val username:String,
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("message")
    val message:String
)