package com.sanam.yavarpour.image_search.data.remote

import com.sanam.yavarpour.image_search.data.dto.ImageSearchResponse
import com.sanam.yavarpour.image_search.data.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/api/")
    suspend fun searchImage(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = Constants.ITEMS_PER_PAGE,
        @Query("key") key: String = "32277343-ad88c2796bb38326eeaeee789"
    ): Response<ImageSearchResponse>
}