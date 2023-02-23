package com.example.mangaread.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaread.databinding.ItemFilterRvBinding
import com.example.mangaread.domain.models.Genre

class GenreAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<Genre, GenreAdapter.FilterViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilterViewHolder(
        ItemFilterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class FilterViewHolder(private val binding: ItemFilterRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(resultManga: Genre) = with(binding) {
            // checkBox logic
            checkbox.text = resultManga.title

            itemView.setOnClickListener {
                onClick(checkbox.text.toString())
                checkbox.isChecked = true
            }
        }
    }

    companion object : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre,
        ) = oldItem == newItem
    }
}