package com.example.mangaread.domain.usecases.manga

import com.example.mangaread.domain.repository.MangaRepository

class AddCommentUseCase constructor(
    private val mangaRepository: MangaRepository,
) {
    fun addComment(
        accessToken: String,
        id: Int,
        text: String,
    ) = mangaRepository.addComment(accessToken, id, text)
}