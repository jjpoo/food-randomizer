package com.polina.test.task.food.randomizer.data.repository

import com.polina.test.task.food.randomizer.data.api.RandomApi
import com.polina.test.task.food.randomizer.data.mapper.toDomain
import com.polina.test.task.food.randomizer.domain.model.Description
import com.polina.test.task.food.randomizer.domain.model.Random
import com.polina.test.task.food.randomizer.domain.repository.RandomRepository
import com.polina.test.task.food.randomizer.domain.util.Resource
import javax.inject.Inject

class RandomRepositoryImpl @Inject constructor(
    private val api: RandomApi
) : RandomRepository {
    override suspend fun getRandomItems(): Resource<Random> {
        return try {
            Resource.Success(
                data = api.getRandomItems().toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "OH, NO! YOU HAVE AN ERROR!"
            )
        }
    }

    override suspend fun getItemDetails(id: String): Resource<Description> {
        return try {
            Resource.Success(
                data = api.getItemDetails(id).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "OH, NO! YOU HAVE AN ERROR!"
            )
        }
    }
}