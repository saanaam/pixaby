package com.sanam.yavarpour.image_search.data.remote.dataSource

import com.sanam.yavarpour.core.BaseApiResponse
import com.sanam.yavarpour.core.Resource
import com.sanam.yavarpour.image_search.data.dto.ImageSearchResponse
import com.sanam.yavarpour.image_search.data.remote.SearchService
import com.sanam.yavarpour.image_search.data.repositoty.datasource.SearchImageRemoteSource
import javax.inject.Inject

class SearchImageRemoteDataSource @Inject constructor(
    private val searchService: SearchService
) : BaseApiResponse(), SearchImageRemoteSource {
    override suspend fun searchImage(query: String, page: Int): Resource<ImageSearchResponse> {
        return safeApiCall { searchService.searchImage(query, page) }
    }
}