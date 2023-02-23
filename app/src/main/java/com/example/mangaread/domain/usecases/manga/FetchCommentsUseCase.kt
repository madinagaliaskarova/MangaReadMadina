package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class FetchCommentsUseCase constructor(
    private val mangaRepository: MangaRepository
) {
    fun fetchComments(id:Int) = mangaRepository.fetchComments(id)
}