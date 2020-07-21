package com.desarrollodroide.simplecleancode.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.desarrollodroide.simplecleancode.R
import com.desarrollodroide.simplecleancode.model.DummyObject

class MainAdapter(private val dataset: List<DummyObject>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) :RecyclerView.ViewHolder(v) {
        val tvTitle: TextView = v.findViewById(R.id.tv_title)
        val tvSubtitle: TextView = v.findViewById(R.id.tv_subTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = dataset[position].title
        holder.tvSubtitle.text = dataset[position].body
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
