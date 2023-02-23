package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchMangaByGenreUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchMangaByGenre(genre:String) = mangaRepository.fetchMangaByGenre(genre)
}