package com.polina.test.task.food.randomizer.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.polina.test.task.food.randomizer.R
import com.polina.test.task.food.randomizer.common.Base10Dp
import com.polina.test.task.food.randomizer.common.Base16Dp
import com.polina.test.task.food.randomizer.common.Base20Dp
import com.polina.test.task.food.randomizer.common.Base60Dp
import com.polina.test.task.food.randomizer.domain.model.Random
import com.polina.test.task.food.randomizer.domain.model.RandomItem
import com.polina.test.task.food.randomizer.presentation.state.RandomUiEvent
import com.polina.test.task.food.randomizer.presentation.state.RandomUiState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RandomListScreen(
    uiState: RandomUiState,
    onEvent: (RandomUiEvent) -> Unit,
    onCardClicked: (String, String, Color, String) -> Unit
) {
    Scaffold(
        topBar = {
            RandomTopBar(
                title = uiState.title,
                onRefreshClicked = {
                    onEvent(RandomUiEvent.RefreshPage)
                }
            )
        }
    ) {
        LaunchedEffect(
            key1 = Unit,
            block = { onEvent(RandomUiEvent.LoadListData) }
        )
        Box(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Base20Dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    uiState.items?.let { items ->
                        RandomList(
                            randomItems = items,
                            onCardClicked = onCardClicked
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RandomTopBar(
    title: String,
    onRefreshClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
        actions = {
            IconButton(
                onClick = onRefreshClicked
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_refresh_button),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun RandomList(
    randomItems: List<RandomItem>,
    onCardClicked: (String, String, Color, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Base16Dp),
        verticalArrangement = Arrangement.spacedBy(Base16Dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(randomItems) { item: RandomItem ->

            item.image?.let { image ->
                RandomListItem(
                    name = item.name,
                    image = image,
                    color = item.color,
                    id = item.id,
                    onCardClicked = onCardClicked
                )
            }
        }
    }
}


@Composable
fun RandomListItem(
    name: String,
    image: String,
    color: Color,
    id: String,
    onCardClicked: (String, String, Color, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClicked.invoke(id, image, color, name) },
        shape = RoundedCornerShape(Base10Dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
                color = Color.White
            )
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.size(Base60Dp)
            )
        }
    }
}

@Preview
@Composable
fun RandomTopBarPreview() {
    RandomTopBar(
        title = "Fruits",
        onRefreshClicked = {}
    )
}

@Preview
@Composable
fun RandomListScreenPreview() {
    val randomItem1 = RandomItem(
        "1",
        "Cucumber",
        "",
        Color.Gray
    )
    val randomItem2 = RandomItem(
        "2",
        "Tomato",
        "",
        Color.Gray
    )

    val random = Random(
        randomTitle = "Fruits",
        randomItems = listOf(
            randomItem1, randomItem2
        )
    )
    RandomList(
        randomItems = random.randomItems,
        onCardClicked = { _, _, _, _ -> }
    )
}