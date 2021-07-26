package ru.nobird.hse2021.sample.githubuserlist.domain.model

data class PagedData<D>(
    val page: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean,
    val data: D
)