package com.example.mangaread.presentation.di


import com.example.mangaread.presentation.activity.AuthViewModel
import com.example.mangaread.presentation.activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules : Module = module {
    viewModel { MainViewModel(get()) }
    viewModel { AuthViewModel(get()) }
}