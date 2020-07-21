package com.desarrollodroide.simplecleancode.di

fun configureTestAppComponent(baseApi: String) = listOf( configureNetworkModuleForTest(baseApi) )

