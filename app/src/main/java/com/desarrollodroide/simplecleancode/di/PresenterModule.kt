package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presenterModule() = module {
  viewModel { MainViewModel(get()) }
}