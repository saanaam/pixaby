package com.sanam.yavarpour.image_search.domain.repository

import androidx.paging.PagingData
import com.sanam.yavarpour.core.model.Hit
import kotlinx.coroutines.flow.Flow

interface SearchImageRepository {
    suspend fun searchImage(query: String): Flow<PagingData<Hit>>
}