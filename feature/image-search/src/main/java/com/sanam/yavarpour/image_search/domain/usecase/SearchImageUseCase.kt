package com.sanam.yavarpour.image_search.domain.usecase

import androidx.paging.PagingData
import com.sanam.yavarpour.core.model.Hit
import com.sanam.yavarpour.image_search.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImageUseCase @Inject constructor(
    private val repository: SearchImageRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<Hit>> =
        repository.searchImage(query)
}
