package com.polina.test.task.food.randomizer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.polina.test.task.food.randomizer.presentation.screens.DetailsScreen
import com.polina.test.task.food.randomizer.presentation.screens.RandomListScreen
import com.polina.test.task.food.randomizer.presentation.state.RandomUiState
import com.polina.test.task.food.randomizer.presentation.state.RandomViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigationGraph(
    navController: NavHostController,
    uiState: RandomUiState,
    viewModel: RandomViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RandomListScreen.route
    ) {
        composable(
            route = Screen.RandomListScreen.route,
            content = {
                RandomListScreen(
                    uiState = uiState,
                    onEvent = viewModel::handleUiEvent,
                    onCardClicked = { id, image, color, name ->
                        val encodedImageUrl =
                            URLEncoder.encode(image, StandardCharsets.UTF_8.toString())

                        navController.navigate(
                            route = Screen.RandomDetailsScreen.getScreenWithArguments(
                                id = id,
                                image = encodedImageUrl,
                                color = color,
                                name = name
                            )
                        )
                    }
                )
            }
        )

        composable(
            route = Screen.RandomDetailsScreen.route,
            content = { backStackEntry ->
                val idArg = backStackEntry.arguments?.getString("id")
                val imageArg = backStackEntry.arguments?.getString("image")
                val decodedImage = URLDecoder.decode(imageArg, "UTF-8")
                val colorArg = backStackEntry.arguments?.getString("color")
                val color = remember {
                    colorArg?.let { Color(it.toInt()) } ?: Color.White
                }
                val name = backStackEntry.arguments?.getString("name")

                DetailsScreen(
                    uiState = uiState,
                    id = idArg.orEmpty(),
                    name = name.orEmpty(),
                    image = decodedImage.orEmpty(),
                    color = color,
                    onBackClicked = {
                        navController.popBackStack()
                    },
                    onEvent = viewModel::handleUiEvent
                )
            }
        )
    }
}