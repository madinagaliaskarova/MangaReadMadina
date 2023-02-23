package com.example.mangaread.presentation.di

import com.example.mangaread.data.local.prefsModule
import com.example.mangaread.data.network.networkModule
import com.example.mangaread.data.repository.dataSourceImpl

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    prefsModule,
    dataSourceImpl
)