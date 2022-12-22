package com.sanam.yavarpour.core.di

import com.sanam.yavarpour.core.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class CoreModule {
    @Provides
    fun provideAppDispatcher() =
        AppDispatchers(Dispatchers.Main, Dispatchers.IO, Dispatchers.Default)
}