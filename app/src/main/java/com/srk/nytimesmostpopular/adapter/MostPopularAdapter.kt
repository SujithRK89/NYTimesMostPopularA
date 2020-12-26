package com.srk.nytimesmostpopular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.srk.nytimesmostpopular.R
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.databinding.ItemMostPopularBinding
import com.srk.nytimesmostpopular.handler.MostPopularHandler
import kotlin.coroutines.coroutineContext


/**
 * Created by sujithrk on 17,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
class MostPopularAdapter(private val listener: MostPopularHandler): RecyclerView.Adapter<MostPopularViewHolder>() {

    private val items = ArrayList<MostPopularResult>()

    fun setItems(items: ArrayList<MostPopularResult>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        val binding: ItemMostPopularBinding = ItemMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MostPopularViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}

class MostPopularViewHolder(private val itemBinding: ItemMostPopularBinding, private val listener: MostPopularHandler): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var mostPopular: MostPopularResult

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: MostPopularResult) {

        this.mostPopular = item
        itemBinding.mostPopularResult = item
    }

    override fun onClick(v: View?) {
        listener.onItemClick(mostPopular)
    }

}