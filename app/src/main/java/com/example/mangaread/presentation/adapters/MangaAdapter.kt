package com.example.mangaread.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaread.databinding.ItemMangaRvBinding
import com.example.mangaread.domain.models.Manga
import com.example.mangaread.domain.utils.extensions.loadImage

class MangaAdapter(
    private val onItemClick: (position: Int) -> Unit) :
    ListAdapter<Manga, MangaAdapter.MangaViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MangaViewHolder(
        ItemMangaRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class MangaViewHolder(private val binding: ItemMangaRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(resultManga: Manga) = with(binding) {
            mangaItemImg.loadImage(resultManga.image)

            itemView.setOnClickListener {
                onItemClick(resultManga.id)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<Manga>() {
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga) =
            oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: Manga,
            newItem: Manga
        ) = oldItem == newItem
    }
}