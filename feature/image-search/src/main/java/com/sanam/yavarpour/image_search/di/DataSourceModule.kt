package com.sanam.yavarpour.image_search.di

import com.sanam.yavarpour.image_search.data.local.dao.ImagesDao
import com.sanam.yavarpour.image_search.data.local.dao.ImagesRemoteKeysDao
import com.sanam.yavarpour.image_search.data.local.datasource.ImageLocalDataSource
import com.sanam.yavarpour.image_search.data.local.datasource.ImageLocalKeysDataSource
import com.sanam.yavarpour.image_search.data.remote.SearchService
import com.sanam.yavarpour.image_search.data.remote.dataSource.SearchImageRemoteDataSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalKeysSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.SearchImageRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun provideSearchImageRemoteDataSource(
        searchService: SearchService
    ): SearchImageRemoteSource = SearchImageRemoteDataSource(searchService)

    @Provides
    fun provideImageLocalDataSource(
        imageDao: ImagesDao
    ): ImageLocalSource = ImageLocalDataSource(imageDao)

    @Provides
    fun provideImageLocalKeysDataSource(
        imagesRemoteKeysDao: ImagesRemoteKeysDao
    ): ImageLocalKeysSource = ImageLocalKeysDataSource(imagesRemoteKeysDao)
}