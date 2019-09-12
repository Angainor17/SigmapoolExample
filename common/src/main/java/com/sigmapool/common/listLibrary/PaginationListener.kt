package com.sigmapool.common.listLibrary

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class PaginationListener(
    private val pageSize: Int,
    private val swipeRefreshLayout: SwipeRefreshLayout?,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        swipeRefreshLayout?.isEnabled = layoutManager.findFirstCompletelyVisibleItemPosition() == 0

        val visibleItemCount = layoutManager.childCount - headerCount()
        val totalItemCount = layoutManager.itemCount - headerCount()
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() + headerCount()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= pageSize
            ) {
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    abstract fun headerCount(): Int
}