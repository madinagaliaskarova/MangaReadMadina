package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchMangaByTypeUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchMangaByType(type:String) = mangaRepository.fetchMangaByType(type)
}