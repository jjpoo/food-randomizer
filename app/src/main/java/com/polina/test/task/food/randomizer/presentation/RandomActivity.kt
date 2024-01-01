package com.polina.test.task.food.randomizer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.polina.test.task.food.randomizer.presentation.navigation.NavigationGraph
import com.polina.test.task.food.randomizer.presentation.state.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomActivity : ComponentActivity() {

    private val randomViewModel: RandomViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationGraph(
                navController = rememberNavController(),
                uiState = randomViewModel.state,
                viewModel = randomViewModel
            )
        }
    }
}
