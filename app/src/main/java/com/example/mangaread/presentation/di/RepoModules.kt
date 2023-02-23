package com.example.mangaread.presentation.di

import com.example.mangaread.data.repository.AuthRepositoryImpl
import com.example.mangaread.data.repository.MangaRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules:Module = module {
    factory { MangaRepositoryImpl(get()) }
    single { AuthRepositoryImpl(get()) }
}