package ru.nobird.hse2021.sample.githubuserlist.domain.base

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}