package com.example.mangaread.data.repository

import com.example.mangaread.data.network.service.MangaApiService
import com.example.mangaread.domain.models.*
import com.example.mangaread.domain.repository.MangaRepository
import com.example.mangaread.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.koin.core.module.Module
import org.koin.dsl.module

val dataSourceImpl:Module = module(override = true){
 factory { MangaRepositoryImpl(get()) }
}

class MangaRepositoryImpl(private val mangaApiService: MangaApiService):MangaRepository {

    override fun fetchAllManga(limit: Int, offset: Int): Flow<Resource<MangaInfo>> {
        return flow {
           emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchAllMangaList(limit, offset).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchTopManga(limit: Int, offset: Int): Flow<Resource<MangaInfo>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchTopMangaList(limit, offset).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchGenre(): Flow<Resource<List<Genre>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchGenreList().body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchMangaById(id:Int): Flow<Resource<Manga>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchAllMangaById(id).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchComments(id:Int): Flow<Resource<List<CommentsItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchCommentList(id).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun addComment(
        accessToken: String,
        id: Int,
        text: String,
    ): Flow<Resource<AddCommentModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.addComment(accessToken,id,text).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchMangaBySearch(search: String): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchAllMangaBySearch(search).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchTopMangaBySearch(search: String): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchTopMangaBySearch(search).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchMangaByType(type: String): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchAllMangaByType(type).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }

    override fun fetchMangaByGenre(genre: String): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = mangaApiService.fetchAllMangaByGenre(genre).body()!!))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage as String))
            }
        }
    }
}