package com.example.rickandmorty.character.ui.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.character.ui.adapter.holder.EpisodeHolder

class EpisodeAdapter : ListAdapter<EpisodeResponseItem, EpisodeHolder>(EpisodeItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        return EpisodeHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.binding(getItem(position))
    }
}