package com.polina.test.task.food.randomizer.data.api

import com.polina.test.task.food.randomizer.data.model.DetailsDto
import com.polina.test.task.food.randomizer.data.model.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RandomApi {

    @GET("/items/random")
    suspend fun getRandomItems(): ResponseDto

    @GET("texts/{id}")
    suspend fun getItemDetails(
        @Path("id") id: String
    ): DetailsDto
}