package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.api.ApiService
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configureNetworkModuleForTest(baseApi: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    factory { get<Retrofit>().create(ApiService::class.java) }

}