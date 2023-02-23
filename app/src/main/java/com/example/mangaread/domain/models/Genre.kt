package com.example.mangaread.domain.models

data class GenreInfo(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<Genre>
)

data class Genre(
    val id: Int,
    val title: String
)