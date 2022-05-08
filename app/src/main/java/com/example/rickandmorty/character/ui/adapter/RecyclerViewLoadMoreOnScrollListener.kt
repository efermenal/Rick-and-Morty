package com.example.rickandmorty.character.ui.adapter

import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoadMoreOnScrollListener(
    private val listener: Listener,
) : RecyclerView.OnScrollListener() {

    private var isLoading = false

    interface Listener {
        fun onLoadMore()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (recyclerView.canScrollVertically(1) && isLoading.not()) {
            isLoading = true
            listener.onLoadMore()
        }
    }

    fun loadFinished() {
        isLoading = false
    }
}