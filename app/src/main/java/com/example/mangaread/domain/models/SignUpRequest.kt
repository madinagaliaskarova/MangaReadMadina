package com.example.mangaread.domain.models

import com.google.gson.annotations.SerializedName
import java.io.File

data class SignUpRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("image_file")
    var image_file: File,
    @SerializedName("password")
    val password:String
)