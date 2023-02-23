package com.example.mangaread.domain.models

import com.google.gson.annotations.SerializedName


data class CommentsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_file")
    val image_file: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("username")
    val username: String
)