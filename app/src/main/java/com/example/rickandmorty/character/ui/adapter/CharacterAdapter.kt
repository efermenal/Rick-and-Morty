package com.example.rickandmorty.character.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.ui.adapter.holder.CharacterHolder

class CharacterAdapter(
    private val listener: Listener,
) : ListAdapter<Character, CharacterHolder>(CharacterItemDiffCallback()) {

    interface Listener {
        fun onClickCharacter()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

}