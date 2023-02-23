package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchAllMangaUseCase constructor(
    private val mangaRepository: MangaRepository
) {
   fun fetchAllManga(limit:Int,offset:Int) = mangaRepository.fetchAllManga(limit, offset)
}
