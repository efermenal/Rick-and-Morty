package com.example.rickandmorty.character.ui.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.databinding.RowEpisodeBinding

class EpisodeHolder(
    private val binding: RowEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun binding(episode: EpisodeResponseItem) {
        with(binding) {
            episodeName.text = episode.name
            episodeNumber.text = episode.id.toString()
            episodeAirDate.text = episode.airDate
        }

    }

    companion object {
        fun from(parent: ViewGroup): EpisodeHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.row_episode, parent, false)
            val binding = RowEpisodeBinding.bind(view)
            return EpisodeHolder(binding)
        }
    }

}