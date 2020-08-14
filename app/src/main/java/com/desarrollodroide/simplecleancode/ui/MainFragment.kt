package com.desarrollodroide.simplecleancode.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.desarrollodroide.simplecleancode.R
import com.desarrollodroide.simplecleancode.model.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment(private val viewModel: MainFragmentViewModel) : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        createUI()
    }

    private fun initViewModel() {
        viewModel.dummyObjects.observe(viewLifecycleOwner, Observer { listDummyObjects->
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