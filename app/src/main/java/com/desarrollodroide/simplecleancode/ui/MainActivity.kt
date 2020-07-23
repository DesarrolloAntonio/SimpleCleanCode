package com.desarrollodroide.simplecleancode.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.desarrollodroide.simplecleancode.R
import com.desarrollodroide.simplecleancode.model.Resource
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        createUI()
    }

    private fun initViewModel() {
        viewModel.dummyObjects.observe(this, Observer { listDummyObjects->
            listDummyObjects?.let{
                when(it){
                    is Resource.Success -> {
                        progressCard.visibility = View.INVISIBLE
                        recyclerView.adapter = it.data?.let { dummyObjects -> MainAdapter(dummyObjects) }
                    }
                    is Resource.Error -> {
                        progressCard.visibility = View.INVISIBLE
                        Log.v("Error", it.message?:"")
                        // Manage error
                    }
                    is Resource.Loading -> {
                        progressCard.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun createUI(){
        btnGetData.setOnClickListener {
            it.visibility = View.GONE
            viewModel.getData()
        }
    }
}
