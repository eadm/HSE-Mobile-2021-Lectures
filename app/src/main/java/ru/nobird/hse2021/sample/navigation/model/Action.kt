package ru.nobird.hse2021.sample.navigation.model

interface Action {
    data class ShowMessage(val message: String) : Action
}