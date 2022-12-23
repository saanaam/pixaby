package com.sanam.yavarpour.image_search.data.repositoty.datasource

import com.sanam.yavarpour.core.Resource
import com.sanam.yavarpour.core.model.RemoteSource
import com.sanam.yavarpour.image_search.data.dto.ImageSearchResponse

interface SearchImageRemoteSource : RemoteSource {
    suspend fun searchImage(query: String, page: Int): Resource<ImageSearchResponse>
}