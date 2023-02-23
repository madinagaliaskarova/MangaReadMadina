package com.example.mangaread.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    private var _id = MutableLiveData(0)
    val id : LiveData<Int> = _id

    fun saveId(id:Int){
        _id.value = id
    }

    private var _token = MutableLiveData("")
    val token:LiveData<String> = _token

    fun saveToken(token:String){
        _token.postValue(token)
    }

    private var _type = MutableLiveData("")
    val type:LiveData<String> = _type

    fun saveType(type:String){
        _type.postValue(type)
    }

    private var _genre__title = MutableLiveData("")
    val genre__title:LiveData<String> = _genre__title

    fun saveGenre(genre:String){
        _genre__title.postValue(genre)
    }

    private var _refresh = MutableLiveData("")
    val refresh:LiveData<String> = _refresh
    fun saveRefresh(refresh:String){
        _refresh.postValue(refresh)
    }



}