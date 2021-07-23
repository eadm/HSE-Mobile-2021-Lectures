package ru.nobird.hse2021.sample.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nobird.hse2021.sample.navigation.model.Action
import ru.nobird.hse2021.sample.navigation.model.Item

class IncrementViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Item>())
    val state: Flow<List<Item>>
        get() = _state

    private val _actions = Channel<Action>()
    val actions: Flow<Action> = _actions.receiveAsFlow()

    private var counter = 0
    private var countDelta = 1

    fun onChangeDelta(newDelta: Int) {
        countDelta = newDelta
        viewModelScope.launch {
            _actions.send(Action.ShowMessage(newDelta.toString()))
        }
    }

    fun onAddNewValue() {
        counter += countDelta
        _state.value += Item.WithText(counter.toString())
    }
}