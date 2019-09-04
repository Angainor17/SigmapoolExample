package com.sigmapool.app.screens.miningProfit

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private val PAGE_SIZE = 20

    override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount - headerCount()
        val totalItemCount = layoutManager.itemCount - headerCount()
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() + headerCount()

        if (!isLoading() && !isLastPage()) {
            val a1 = (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
            val a2 = firstVisibleItemPosition >= 0
            val a3 = totalItemCount >= PAGE_SIZE
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
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