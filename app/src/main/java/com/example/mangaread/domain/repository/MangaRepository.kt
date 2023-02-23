package com.example.mangaread.domain.repository

import com.example.mangaread.domain.models.*
import com.example.mangaread.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MangaRepository {

    fun fetchAllManga(limit:Int, offset:Int): Flow<Resource<MangaInfo>>
    fun fetchTopManga(limit:Int, offset:Int): Flow<Resource<MangaInfo>>
    fun fetchGenre():Flow<Resource<List<Genre>>>
    fun fetchMangaById(id:Int):Flow<Resource<Manga>>
    fun fetchComments(id:Int):Flow<Resource<List<CommentsItem>>>
    fun addComment(accessToken:String,id: Int,text:String):Flow<Resource<AddCommentModel>>
    fun fetchMangaBySearch(search:String):Flow<Resource<List<Manga>>>

    fun fetchTopMangaBySearch(search:String):Flow<Resource<List<Manga>>>
    fun fetchMangaByType(type:String):Flow<Resource<List<Manga>>>
    fun fetchMangaByGenre(genre:String):Flow<Resource<List<Manga>>>

}