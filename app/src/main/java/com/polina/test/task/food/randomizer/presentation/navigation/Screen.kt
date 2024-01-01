package com.polina.test.task.food.randomizer.presentation.navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object RandomListScreen : Screen(route = "list_screen")
    object RandomDetailsScreen : Screen(
        route = "details_screen/{id}/{image}/{color}/{name}"
    ) {
        fun getScreenWithArguments(
            id: String,
            image: String,
            color: Color,
            name: String
        ): String {
            val encodedImageUrl = URLEncoder.encode(image, StandardCharsets.UTF_8.toString())

            return "details_screen/$id/$encodedImageUrl/${color.toArgb()}/$name"
        }
    }
}