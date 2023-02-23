package com.example.mangaread.data.mapper

import com.example.mangaread.data.network.models.MangaInfoEntity
import com.example.mangaread.domain.models.MangaInfo

fun MangaInfoEntity.mangaINFEntityToMangaINF() = MangaInfo(
    count = count,
    next = next,
    previous = previous,
    results = results
)

fun MangaInfo.mangaINFToMangaINFEntity() = MangaInfoEntity(
    count = count,
    next = next,
    previous = previous,
    results = results
)