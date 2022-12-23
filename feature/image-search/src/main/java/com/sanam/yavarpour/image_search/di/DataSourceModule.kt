package com.sanam.yavarpour.image_search.di

import com.sanam.yavarpour.image_search.data.remote.SearchService
import com.sanam.yavarpour.image_search.data.remote.dataSource.SearchImageRemoteDataSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.SearchImageRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun provideSearchImageRemoteDataSource(
        searchService: SearchService
    ): SearchImageRemoteSource = SearchImageRemoteDataSource(searchService)
}