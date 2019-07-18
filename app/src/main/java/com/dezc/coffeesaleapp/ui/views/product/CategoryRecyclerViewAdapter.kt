package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dezc.coffeesaleapp.R

class CategoryRecyclerViewAdapter(private val dataset: Array<String>): RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewAdapter.MyViewHolder{
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false) as TextView

        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataset[position]
    }


    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView){

    }
}