package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://jsonplaceholder.typicode.com/"

fun networkingModule() = module {

  single {
    OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
      .build()
  }

  single {
    Retrofit.Builder()
      .addConverterFactory(get<GsonConverterFactory>())
      .client(get<OkHttpClient>())
      .baseUrl(URL)
      .build()
  }

  single { GsonConverterFactory.create() }

  single { get<Retrofit>().create(ApiService::class.java) }
}