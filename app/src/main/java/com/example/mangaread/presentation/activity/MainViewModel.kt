package com.example.mangaread.presentation.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mangaread.data.network.service.MangaApiService
import com.example.mangaread.domain.models.*
import com.example.mangaread.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mangaApiService: MangaApiService
): BaseViewModel() {

    private val _genreState = MutableStateFlow<List<Manga>>(emptyList())
    val genreState = _genreState.asStateFlow()

    private val _filterState = MutableStateFlow(MangaInfo(0,"","", emptyList()))
    val filterState = _filterState.asStateFlow()

    private val _allMangaState = MutableStateFlow(MangaInfo(0,"","", emptyList()))
    val allMangaState = _allMangaState.asStateFlow()

    private val _topHundredState = MutableStateFlow(MangaInfo(0,"","", emptyList()))
    val topHundredState = _topHundredState.asStateFlow()

    private val _commentState = MutableStateFlow<List<CommentsItem>>(emptyList())
    val commentState = _commentState.asStateFlow()

//// Genre
    fun fetchGenre(): LiveData<List<Genre>> {
        val data = MutableLiveData<List<Genre>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchGenreList()
            if (result.isSuccessful){
                data.postValue(result.body())
            }
        }
        return data
    }
//
//
////    AllManga
//
    fun fetchMangaById(id: Int): LiveData<Manga> {
        val data = MutableLiveData<Manga>()
        viewModelScope.launch {
            val result = mangaApiService.fetchAllMangaById(id)
            if (result.isSuccessful){
                data.postValue(result.body())
            }
        }
        return data
    }

    fun fetchAllManga(limit:Int,offset: Int): LiveData<MangaInfo> {
        val data = MutableLiveData<MangaInfo>()
        viewModelScope.launch {
            val result = mangaApiService.fetchAllMangaList(limit, offset)
            if (result.isSuccessful){
                data.postValue(result.body())
            }
        }
        return data
    }
//
    fun fetchMangaBySearch(search:String): LiveData<List<Manga>> {
        val data = MutableLiveData<List<Manga>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchAllMangaBySearch(search)
            if (result.isSuccessful){
                data.postValue(result.body())
            }else{
                Log.e("FAIL COMMENT", "addComment: ${result.message()}", )
            }
        }
        return data
    }

    fun fetchTopMangaBySearch(search:String): LiveData<List<Manga>> {
        val data = MutableLiveData<List<Manga>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchTopMangaBySearch(search)
            if (result.isSuccessful){
                data.postValue(result.body())
            }else{
                Log.e("FAIL Top search", "Top Search: ${result.message()}", )
            }
        }
        return data
    }

    fun fetchMangaByType(type:String): LiveData<List<Manga>> {
        val data = MutableLiveData<List<Manga>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchAllMangaByType(type)
            if (result.isSuccessful){
                data.postValue(result.body())
            }else{
                Log.e("FAIL TYPE", "TYPE: ${result.message()}", )
            }
        }
        return data
    }

    fun fetchMangaByGenre(genre:String): LiveData<List<Manga>> {
        val data = MutableLiveData<List<Manga>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchAllMangaByGenre(genre)
            if (result.isSuccessful){
                data.postValue(result.body())
            }else{
                Log.e("FAIL GENRE", "GENRE: ${result.message()}", )
            }
        }
        return data
    }

////    Comments
    fun addComment(accessToken:String,id: Int,text:String): MutableLiveData<AddCommentModel> {
        val data = MutableLiveData(AddCommentModel(0,""))
        viewModelScope.launch {
            val result = mangaApiService.addComment(accessToken, id, text)
            if (result.isSuccessful){
                data.postValue(result.body())
                Log.e("SUCCESS COMMENT","addComment: ${result.body()?.text}", )
            }else{
                Log.e("FAIL COMMENT", "addComment: ${result.message()}", )
            }
        }
        return data
    }

    fun fetchComments(id: Int): LiveData<List<CommentsItem>> {
        val data = MutableLiveData<List<CommentsItem>>()
        viewModelScope.launch {
            val result = mangaApiService.fetchCommentList(id)
            if (result.isSuccessful){
                data.postValue(result.body())
            }
        }
        return data
    }

////    TopManga
//
    fun fetchTopManga(limit:Int,offset: Int): LiveData<MangaInfo> {
        val data = MutableLiveData<MangaInfo>()
        viewModelScope.launch {
            val result = mangaApiService.fetchTopMangaList(limit, offset)
            if (result.isSuccessful){
                data.postValue(result.body())
            }
        }
        return data
    }

}

