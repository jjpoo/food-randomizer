package com.polina.test.task.food.randomizer.domain.util

sealed class Resource<T>(val data: T?, val message: String?) {

    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message)
    class Error<T>(data: T? = null, message: String?) : Resource<T>(data, message)
}