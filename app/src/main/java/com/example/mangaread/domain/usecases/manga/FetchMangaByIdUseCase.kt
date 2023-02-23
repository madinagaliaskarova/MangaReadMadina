package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchMangaByIdUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchMangaById(id:Int) = mangaRepository.fetchMangaById(id)
}