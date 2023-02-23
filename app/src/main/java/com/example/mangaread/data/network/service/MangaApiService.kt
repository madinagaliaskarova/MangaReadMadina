package com.example.mangaread.data.network.service

import com.example.mangaread.domain.models.*
import retrofit2.Response
import retrofit2.http.*

interface MangaApiService {

//    All Manga
    @GET("api/v1/manga/")
   suspend fun fetchAllMangaList(
        @Query("limit")limit:Int,
        @Query("offset")offset:Int
    ): Response<MangaInfo> // Временно из Domain дальше через Mapping к UseCase

    @GET("api/v1/manga/")
  suspend fun fetchAllMangaBySearch(
        @Query("search")search:String
    ):Response<List<Manga>>

    @GET("api/v1/manga/")
    suspend fun fetchAllMangaByType(
        @Query("type")type:String
    ):Response<List<Manga>>

    @GET("api/v1/manga/")
    suspend fun fetchAllMangaByGenre(
        @Query("genre__title")genre__title:String
    ):Response<List<Manga>>

    @GET("api/v1/manga/{id}/")
    suspend fun fetchAllMangaById(
        @Path("id")id:Int
    ):Response<Manga>


//    Genre
    @GET("api/v1/genre/")
    suspend fun fetchGenreList():Response<List<Genre>> // Временно из Domain дальше через Mapping к UseCase

//  Comment
    @GET("api/v1/manga/{id}/comments/")
   suspend fun fetchCommentList(
        @Path("id")id: Int
    ):Response<List<CommentsItem>>

     @POST("api/v1/manga/{id}/add-comment/")
     @FormUrlEncoded
    suspend fun addComment(
         @Header("Authorization") accessToken:String,
         @Path("id")id: Int,
         @Field("text") text:String,
     ):Response<AddCommentModel>


//    TopManga

    @GET("api/v1/top-manga/")
    suspend fun fetchTopMangaList(
        @Query("limit")limit:Int,
        @Query("offset")offset:Int
    ): Response<MangaInfo>  // Временно из Domain дальше через Mapping к UseCase


    @GET("api/v1/top-manga/")
    suspend fun fetchTopMangaBySearch(
        @Query("search")search:String
    ):Response<List<Manga>>

}