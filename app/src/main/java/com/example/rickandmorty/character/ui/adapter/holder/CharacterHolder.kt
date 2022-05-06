package com.example.rickandmorty.character.ui.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.ui.adapter.CharacterAdapter
import com.example.rickandmorty.databinding.RowCharacterBinding

class CharacterHolder(
    private val binding: RowCharacterBinding,
    listener: CharacterAdapter.Listener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener.onClickCharacter()
        }
    }

    fun bind(character: Character) {
        with(binding) {
            characterName.text = character.name
        }
    }


    companion object {
        fun from(parent: ViewGroup, listener: CharacterAdapter.Listener): CharacterHolder {
            return CharacterHolder(
                binding = RowCharacterBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_character, parent, false),
                ),
                listener = listener
            )
        }
    }
}