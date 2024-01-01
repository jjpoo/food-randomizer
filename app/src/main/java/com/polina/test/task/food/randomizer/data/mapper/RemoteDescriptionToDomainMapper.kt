package com.polina.test.task.food.randomizer.data.mapper

import com.polina.test.task.food.randomizer.data.model.DetailsDto
import com.polina.test.task.food.randomizer.domain.model.Description

fun DetailsDto.toDomain(): Description {
    return Description(
        info = text
    )
}