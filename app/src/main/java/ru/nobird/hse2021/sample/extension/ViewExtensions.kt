package ru.nobird.hse2021.sample.extension

import android.view.View
import androidx.annotation.StringRes
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