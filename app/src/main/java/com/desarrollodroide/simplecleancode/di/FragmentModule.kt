package com.desarrollodroide.simplecleancode.di

import com.desarrollodroide.simplecleancode.ui.MainFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

fun fragmentModule() = module {
    fragment { MainFragment(get()) }
}