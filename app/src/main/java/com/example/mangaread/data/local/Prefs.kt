package com.example.mangaread.data.local

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val prefsModule:Module= module {
    single { Prefs(androidContext()) }
}
class Prefs(context: Context?) {

    private val prefs = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveState(){
        prefs?.edit()?.putBoolean("isShown",true)?.apply()
    }
    fun isShown():Boolean?{
        return prefs?.getBoolean("isShown",false)
    }

    fun saveToken(token: String?) {
        prefs?.edit()?.putString("token", token)?.apply()
    }
    fun giveToken(): String? {
        return prefs?.getString("token", "")
    }
}