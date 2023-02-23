package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchTopMangaUseCase constructor(

    private val mangaRepository: MangaRepository
) {
    fun fetchTopManga(limit:Int,offset:Int) = mangaRepository.fetchTopManga(limit, offset)
}