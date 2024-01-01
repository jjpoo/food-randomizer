package com.polina.test.task.food.randomizer.domain.util

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import com.polina.test.task.food.randomizer.common.Constants

fun String.getColor(): Color {
    val stringColor = "#$this"
    return Color(parseColor(stringColor))
}

fun String?.getImageUrl(): String {
    return "${Constants.BASE_URL}${this}"
}
