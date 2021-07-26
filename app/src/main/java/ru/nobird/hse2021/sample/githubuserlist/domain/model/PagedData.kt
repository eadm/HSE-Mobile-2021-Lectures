package ru.nobird.hse2021.sample.githubuserlist.domain.model

data class PagedData<D>(
    val page: Int = 1,
    val hasNext: Boolean = false,
    val hasPrev: Boolean = false,
    val data: D
) {
    inline fun merge(pagedData: PagedData<D>, acc: (D, D) -> D): PagedData<D> =
        PagedData(
            page = pagedData.page,
            hasNext = pagedData.hasNext,
            hasPrev = this.hasPrev,
            data = acc(this.data, pagedData.data)
        )
}