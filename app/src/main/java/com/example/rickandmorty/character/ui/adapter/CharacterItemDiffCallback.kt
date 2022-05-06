package com.example.rickandmorty.character.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.character.domain.Character

class CharacterItemDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}