package com.sanam.yavarpour.image_search.di

import android.content.Context
import androidx.room.Room
import com.sanam.yavarpour.image_search.data.local.PixabayDatabase
import com.sanam.yavarpour.image_search.data.local.dao.ImagesDao
import com.sanam.yavarpour.image_search.data.local.dao.ImagesRemoteKeysDao
import com.sanam.yavarpour.image_search.data.util.Constants.IMAGES_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PixabayDatabase {
        return Room.databaseBuilder(
            context,
            PixabayDatabase::class.java,
            IMAGES_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideRequestBanner(
        database: PixabayDatabase
    ): ImagesDao = database.imagesDao()

    @Provides
    @Singleton
    fun provideRequestZones(
        database: PixabayDatabase
    ): ImagesRemoteKeysDao = database.imagesRemoteKeysDao()
}