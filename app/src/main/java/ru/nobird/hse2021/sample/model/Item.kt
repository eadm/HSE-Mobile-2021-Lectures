package ru.nobird.hse2021.sample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface Item : Parcelable {
    @Parcelize
    data class Header(val title: String) : Item
    @Parcelize
    data class WithText(val text: String) : Item
}
