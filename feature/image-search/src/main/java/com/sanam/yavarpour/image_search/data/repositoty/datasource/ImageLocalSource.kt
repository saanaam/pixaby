package com.sanam.yavarpour.image_search.data.repositoty.datasource

import androidx.paging.PagingSource
import com.sanam.yavarpour.image_search.data.dto.HitDto

interface ImageLocalSource {
    fun getAllImages(): PagingSource<Int, HitDto>
    suspend fun addImages(images: List<HitDto>)
    suspend fun deleteAllImages()
}