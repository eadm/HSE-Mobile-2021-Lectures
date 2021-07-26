package ru.nobird.hse2021.sample.extension

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

/**
 * Shows snackbar at current view
 */
inline fun View.snackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT, action: Snackbar.() -> Unit = {}) {
    snackbar(context.getString(messageRes), length, action)
}

/**
 * Shows snackbar at current view
 */
inline fun View.snackbar(message: String, length: Int = Snackbar.LENGTH_SHORT, action: Snackbar.() -> Unit = {}) {
    Snackbar
        .make(this, message, length)
        .apply(action)
        .show()
}

fun RecyclerView.setOnPaginationListener(loadNextPage: () -> Unit): RecyclerView.OnScrollListener =
    object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = (recyclerView.layoutManager as? LinearLayoutManager)
                ?: return


            val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

            val delta = if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                dx
            } else {
                dy
            }

            if (delta > 0) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    post { loadNextPage() }
                }
            } else {
                if (pastVisibleItems == 0) {
                    // handle load prev page
                }
            }
        }
    }.also(this::addOnScrollListener)