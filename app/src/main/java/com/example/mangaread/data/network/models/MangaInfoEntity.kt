package com.example.mangaread.data.network.models

import com.example.mangaread.domain.models.Manga
import com.google.gson.annotations.SerializedName

data class MangaInfoEntity(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Manga>
)

data class MangaEntity(
    @SerializedName("chapters_quantity")
    val chapters_quantity: Int,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("en_name")
    val en_name: String,
    @SerializedName("genre")
    val genre: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("issue_year")
    val issue_year: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("ru_name")
    val ru_name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updated_at: String
)
