package com.polina.test.task.food.randomizer.domain.repository

import com.polina.test.task.food.randomizer.domain.model.Description
import com.polina.test.task.food.randomizer.domain.model.Random
import com.polina.test.task.food.randomizer.domain.util.Resource

interface RandomRepository {
    suspend fun getRandomItems(): Resource<Random>
    suspend fun getItemDetails(id: String): Resource<Description>
}