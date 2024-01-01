package com.polina.test.task.food.randomizer.presentation.state

sealed class RandomUiEvent {
    object LoadListData : RandomUiEvent()
    object RefreshPage : RandomUiEvent()
    data class LoadDetailsData(val id: String) : RandomUiEvent()
}