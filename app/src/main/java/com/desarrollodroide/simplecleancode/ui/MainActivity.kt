package com.desarrollodroide.simplecleancode.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.desarrollodroide.simplecleancode.R
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, null)
                .commit()
        }
    }
}
