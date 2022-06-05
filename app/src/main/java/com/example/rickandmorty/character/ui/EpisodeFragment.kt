package com.example.rickandmorty.character.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodeBinding


class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    private val args: EpisodeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEpisodeBinding.bind(view)

        with(binding) {
            // episodeContentView.adapter =
        }


    }

}