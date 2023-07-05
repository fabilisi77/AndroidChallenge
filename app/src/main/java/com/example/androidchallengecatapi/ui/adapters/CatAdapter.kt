package com.example.androidchallengecatapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.databinding.ItemCatBinding

class CatAdapter(private val itemClickListener: OnCatClickListener) :
    ListAdapter<Cat, CatAdapter.CatViewHolder>(CatDiffCallback()) {

    interface OnCatClickListener {
        fun onCatClick(cat: Cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding =
            ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    inner class CatViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val cat = getItem(position)
                    itemClickListener.onCatClick(cat)
                }
            }
        }

        fun bind(cat: Cat) {
            Glide.with(binding.root)
                .load(cat.url)
                .into(binding.imgCat)
        }
    }

    class CatDiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }
}




