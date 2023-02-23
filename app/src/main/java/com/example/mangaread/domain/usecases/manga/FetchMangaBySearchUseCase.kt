package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchMangaBySearchUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchMangaBySearch(search:String) = mangaRepository.fetchMangaBySearch(search)
}