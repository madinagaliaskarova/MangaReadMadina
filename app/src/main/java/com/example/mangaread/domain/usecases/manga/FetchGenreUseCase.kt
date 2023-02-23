package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchGenreUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchGenre() = mangaRepository.fetchGenre()
}