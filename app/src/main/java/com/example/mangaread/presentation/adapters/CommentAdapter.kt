package com.example.mangaread.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaread.R
import com.example.mangaread.databinding.ItemCommentsRvBinding
import com.example.mangaread.domain.models.CommentsItem
import com.example.mangaread.domain.utils.extensions.loadImage


class CommentAdapter :
    ListAdapter<CommentsItem, CommentAdapter.CommentViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentViewHolder(
        ItemCommentsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CommentViewHolder(private val binding: ItemCommentsRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(commentItem: CommentsItem) = with(binding) {
            userImg.loadImage(commentItem.user.image_file)
            userName.text = commentItem.user.nickname
            userComment.text = commentItem.text

        }

    }

    companion object : DiffUtil.ItemCallback<CommentsItem>() {
        override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem) =
            oldItem.user.id == newItem.user.id

        override fun areContentsTheSame(
            oldItem: CommentsItem,
            newItem: CommentsItem
        ) = oldItem == newItem
    }
}