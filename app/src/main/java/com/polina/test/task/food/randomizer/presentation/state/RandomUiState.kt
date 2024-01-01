package com.polina.test.task.food.randomizer.presentation.state

import com.polina.test.task.food.randomizer.domain.model.RandomItem

data class RandomUiState(
    val title: String = "",
    val items: List<RandomItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val info: String = "",
    val id: String = ""
)
