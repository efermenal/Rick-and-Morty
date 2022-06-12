package com.example.rickandmorty.character.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.character.ui.adapter.EpisodeAdapter
import com.example.rickandmorty.databinding.FragmentEpisodeBinding


class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    private val args: EpisodeFragmentArgs by navArgs()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEpisodeBinding.bind(view)

        setAdapter(binding)
    }

    private fun setAdapter(binding: FragmentEpisodeBinding) {
        episodeAdapter = EpisodeAdapter()
        with(binding) {
            episodeContentView.adapter = episodeAdapter
        }
        episodeAdapter.submitList(args.episodeList.episodes)
    }

}