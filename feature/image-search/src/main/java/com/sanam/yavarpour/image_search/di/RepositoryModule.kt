package com.sanam.yavarpour.image_search.di

import com.sanam.yavarpour.image_search.data.repositoty.SearchImageDataRepository
import com.sanam.yavarpour.image_search.domain.repository.SearchImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchImageRepository(
        searchImageDataRepository: SearchImageDataRepository
    ): SearchImageRepository
}