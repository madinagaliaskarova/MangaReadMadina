package com.example.mangaread.domain.models


import com.google.gson.annotations.SerializedName

data class AddCommentModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String
)