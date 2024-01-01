package com.polina.test.task.food.randomizer.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.polina.test.task.food.randomizer.common.Base10Dp
import com.polina.test.task.food.randomizer.common.Base16Dp
import com.polina.test.task.food.randomizer.common.Base20Dp
import com.polina.test.task.food.randomizer.common.Base70Dp
import com.polina.test.task.food.randomizer.common.Base8Dp
import com.polina.test.task.food.randomizer.presentation.state.RandomUiEvent
import com.polina.test.task.food.randomizer.presentation.state.RandomUiState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailsScreen(
    image: String,
    color: Color,
    name: String,
    id: String,
    uiState: RandomUiState,
    onBackClicked: () -> Unit,
    onEvent: (RandomUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                title = name,
                onBackClicked = onBackClicked,
            )
        }
    ) {
        LaunchedEffect(
            key1 = Unit,
            block = {
                onEvent(RandomUiEvent.LoadDetailsData(id))
            }
        )
        Box(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = Base20Dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Base20Dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Magenta
                    )
                } else {
                    DetailsScreenContent(
                        image = image,
                        color = color,
                        detailsInfo = uiState.info
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailsTopBar(
    title: String,
    onBackClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
        navigationIcon = {
            IconButton(
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun DetailsScreenContent(
    image: String,
    color: Color,
    detailsInfo: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Base10Dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Base16Dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.size(Base70Dp)
            )
            Text(
                text = detailsInfo,
                modifier = Modifier.padding(top = Base8Dp),
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun DetailsTopBarPreview() {
    DetailsTopBar(
        title = "Tomato",
        onBackClicked = {}
    )
    DetailsScreenContent(
        image = "",
        color = Color.Gray,
        detailsInfo = "Details info about tomato. It's very tasty and healthy! " +
                "You should eat more vegetables!"
    )
}