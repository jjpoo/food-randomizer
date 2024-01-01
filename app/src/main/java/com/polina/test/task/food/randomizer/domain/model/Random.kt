package com.polina.test.task.food.randomizer.domain.model

import androidx.compose.ui.graphics.Color

data class Random(
    val randomTitle: String,
    val randomItems: List<RandomItem>
)

data class RandomItem(
    val id: String,
    val name: String,
    val image: String?,
    val color: Color
)