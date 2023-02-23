package com.example.mangaread.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MangaInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Manga>
)

data class Manga(
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
): Serializable{
    constructor():this(0,
        "",
        "",
        "",
        "",
        emptyList(),
        0,
        "",
        0,
        0,
        0.0,
        "",
        "",
        "")
}
