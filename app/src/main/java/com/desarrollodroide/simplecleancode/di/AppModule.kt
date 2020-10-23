package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.database.DummyObjectDatabase
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepository
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {


  single { DummyObjectDatabase.create(androidContext()) }

  single { get<DummyObjectDatabase>().dummyObjectDao() }

  single { DummyObjectRepositoryImpl(get(), get()) as DummyObjectRepository }

}