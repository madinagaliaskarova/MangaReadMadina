package com.example.mangaread.data.network

import com.example.mangaread.data.network.service.AuthApiService
import com.example.mangaread.data.network.service.MangaApiService
import com.example.mangaread.data.repository.AuthRepositoryImpl
import com.example.mangaread.data.repository.MangaRepositoryImpl
import com.example.mangaread.domain.repository.AuthRepository
import com.example.mangaread.domain.repository.MangaRepository
import com.example.mangaread.domain.utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule: Module = module {
    factory { provideOkhttpClient() }
    factory { provideMangaApi(get()) }
    factory { provideAuthApi(get()) }
    single { provideRetrofit(get()) }
    single { provideMangaRepository(get()) }
    single { provideAuthRepository(get()) }
}
private fun provideOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Const.Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

private fun provideMangaApi(retrofit: Retrofit): MangaApiService {
    return retrofit.create(MangaApiService::class.java)
}

private fun provideAuthApi(retrofit: Retrofit): AuthApiService {
    return retrofit.create(AuthApiService::class.java)
}

private fun provideMangaRepository(mangaApiService: MangaApiService):MangaRepository = MangaRepositoryImpl(mangaApiService)
private fun provideAuthRepository(authApiService: AuthApiService):AuthRepository = AuthRepositoryImpl(authApiService)
