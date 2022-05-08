package com.example.rickandmorty.character.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.character.ui.adapter.CharacterAdapter
import com.example.rickandmorty.character.ui.adapter.CharacterAdapter.Companion.VIEW_TYPE_ITEM
import com.example.rickandmorty.character.ui.adapter.CharacterAdapter.Companion.VIEW_TYPE_LOADING
import com.example.rickandmorty.character.ui.adapter.RecyclerViewLoadMoreOnScrollListener
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character),
    CharacterAdapter.Listener,
    RecyclerViewLoadMoreOnScrollListener.Listener {

    private val viewModel: CharacterViewModel by viewModels()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var onScrollListener: RecyclerViewLoadMoreOnScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        configureUI()
        observeViewModel()
        viewModel.getCharacters()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickCharacter() {

    }

    private fun configureUI() {
        characterAdapter = CharacterAdapter(this)
        gridLayoutManager = GridLayoutManager(activity, CHARACTER_PER_ROW)
        binding.characterContentView.apply {
            adapter = characterAdapter
            layoutManager = gridLayoutManager
        }
        // needed to define number of spans each item occupies
        // when the type is VIEW_TYPE_LOADING, the loader must occupy all the row
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (characterAdapter.getItemViewType(position)) {
                    VIEW_TYPE_ITEM -> 1
                    VIEW_TYPE_LOADING -> CHARACTER_PER_ROW
                    else -> 1
                }
            }
        }

        onScrollListener = RecyclerViewLoadMoreOnScrollListener(this)
        binding.characterContentView.addOnScrollListener(onScrollListener)

    }

    override fun onLoadMore() {
        viewModel.getCharacters()
    }

    private fun observeViewModel() {
        viewModel.viewState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                renderUI(it)
            }.launchIn(lifecycleScope)
    }

    private fun renderUI(characterState: CharacterViewModel.ViewState) {

        when (characterState.isLoading) {
            true -> {
                characterAdapter.addLoadingView()
            }
            false -> {
                characterAdapter.submitList(characterState.characters)
                onScrollListener.loadFinished()
            }
        }

    }

    companion object {
        private const val CHARACTER_PER_ROW = 4
    }


}