package com.example.rickandmorty.character.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.character.domain.EpisodeResponseItem

class EpisodeItemDiffCallback : DiffUtil.ItemCallback<EpisodeResponseItem>() {
    override fun areItemsTheSame(
        oldItem: EpisodeResponseItem,
        newItem: EpisodeResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EpisodeResponseItem,
        newItem: EpisodeResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}