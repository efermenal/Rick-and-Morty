package com.example.rickandmorty.character.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoadMoreOnScrollListener(
    private val listener: Listener,
    private val layoutManager: RecyclerView.LayoutManager
) : RecyclerView.OnScrollListener() {

    private var isLoading = false
    private var lastVisibleItem: Int = 0
    private var itemOnRecycler: Int = 0
    private var visibleThreshold = 1

    init {
        if (layoutManager is GridLayoutManager) {
            visibleThreshold *= layoutManager.spanCount
        }
    }

    interface Listener {
        fun onLoadMore()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //  prevent subtle bug and improve performance
        if (dy <= 0) return

        if (layoutManager is GridLayoutManager) {
            lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        }

        itemOnRecycler = layoutManager.itemCount

        if (itemOnRecycler <= lastVisibleItem + visibleThreshold && isLoading.not()) {
            isLoading = true
            listener.onLoadMore()
        }
    }

    fun loadFinished() {
        isLoading = false
    }
}