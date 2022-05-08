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
        return if (viewType == VIEW_TYPE_ITEM) {
            CharacterHolder.CharacterItemHolder.from(parent, listener)
        } else {
            CharacterHolder.LoadingItemHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        if (holder is CharacterHolder.CharacterItemHolder) {
            val character = getItem(position)
            holder.bind(character)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val character = getItem(position)
        return if (character != null) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    fun addLoadingView() {
        //  double check to prevent loading view when is not needed
        if (currentList.size != 0 && currentList.contains(null).not()) {
            val newList = currentList + null
            submitList(newList)
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

}