package com.polina.test.task.food.randomizer.data.di

import com.polina.test.task.food.randomizer.data.repository.RandomRepositoryImpl
import com.polina.test.task.food.randomizer.domain.repository.RandomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRandomRepository(
        coinsRepositoryImpl: RandomRepositoryImpl
    ): RandomRepository
}