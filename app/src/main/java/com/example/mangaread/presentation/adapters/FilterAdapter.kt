package com.example.mangaread.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaread.databinding.ItemFilterRvBinding
import com.example.mangaread.domain.models.Manga

class FilterAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<Manga, FilterAdapter.FilterViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilterViewHolder(
        ItemFilterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class FilterViewHolder(private val binding: ItemFilterRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(resultManga: Manga) = with(binding) {
            checkbox.text = resultManga.type

            itemView.setOnClickListener {
                onClick(checkbox.text.toString())
                checkbox.isChecked = true
            }
        }
    }

    companion object : DiffUtil.ItemCallback<Manga>() {
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Manga,
            newItem: Manga,
        ) = oldItem == newItem
    }
}