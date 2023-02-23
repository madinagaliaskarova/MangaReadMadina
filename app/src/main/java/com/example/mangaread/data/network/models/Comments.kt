package com.example.mangaread.data.network.models


data class CommentsItem(
    val id: Int,
    val text: String,
    val user: UserEntity
)

data class UserEntity(
    val id: Int,
    val image: String,
    val image_file: String,
    val nickname: String,
    val username: String
)