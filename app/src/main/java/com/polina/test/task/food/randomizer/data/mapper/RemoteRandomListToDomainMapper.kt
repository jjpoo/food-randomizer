package com.polina.test.task.food.randomizer.data.mapper

import com.polina.test.task.food.randomizer.data.model.RandomResponseItem
import com.polina.test.task.food.randomizer.data.model.ResponseDto
import com.polina.test.task.food.randomizer.domain.model.Random
import com.polina.test.task.food.randomizer.domain.model.RandomItem
import com.polina.test.task.food.randomizer.domain.util.getColor
import com.polina.test.task.food.randomizer.domain.util.getImageUrl

fun ResponseDto.toDomain(): Random {
    return Random(
        randomTitle = title,
        randomItems = items.map { it.toDomain() }
    )
}

fun RandomResponseItem.toDomain(): RandomItem {
    return RandomItem(
        id = id,
        name = name,
        image = image?.getImageUrl(),
        color = color.getColor()
    )
}

