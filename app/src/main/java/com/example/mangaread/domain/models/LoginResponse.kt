package com.example.mangaread.domain.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: String
)