package com.example.rickandmorty.character.ui.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.ui.adapter.CharacterAdapter
import com.example.rickandmorty.databinding.RowCharacterBinding
import com.example.rickandmorty.databinding.RowLoadingBinding
import com.example.rickandmorty.global.loadFromUrl

sealed class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class CharacterItemHolder(
        private val binding: RowCharacterBinding,
        listener: CharacterAdapter.Listener,
    ) : CharacterHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onClickCharacter()
            }
        }

        fun bind(character: Character) {
            with(binding) {
                characterName.text = character.name
                characterImage.loadFromUrl(character.image)
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: CharacterAdapter.Listener): CharacterItemHolder {
                return CharacterItemHolder(
                    binding = RowCharacterBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.row_character, parent, false),
                    ),
                    listener = listener
                )
            }
        }
    }

    class LoadingItemHolder(
        private val binding: RowLoadingBinding
    ) : CharacterHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): LoadingItemHolder {
                return LoadingItemHolder(
                    binding = RowLoadingBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.row_loading, parent, false)
                    )
                )
            }

        }
    }
}