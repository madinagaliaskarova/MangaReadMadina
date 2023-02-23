package com.example.mangaread.data.network.models

data class GenreInfo(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<GenreEntity>
)

data class GenreEntity(
    val id: Int,
    val title: String
)