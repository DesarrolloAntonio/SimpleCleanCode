package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.domain.DummyObjectRepository
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepositoryImpl
import org.koin.dsl.module

fun appModule() = module {
  single { DummyObjectRepositoryImpl(get()) as DummyObjectRepository }
}