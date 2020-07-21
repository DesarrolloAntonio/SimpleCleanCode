package com.desarrollodroide.simplecleancode

import android.app.Application
import com.desarrollodroide.simplecleancode.di.appModule
import com.desarrollodroide.simplecleancode.di.networkingModule
import com.desarrollodroide.simplecleancode.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CleanApp : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@CleanApp)
      modules(listOf(appModule(), networkingModule(), presenterModule()))
    }
  }
}