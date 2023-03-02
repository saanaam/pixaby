package com.sanam.yavarpour.image_search.data.local.datasource

import androidx.paging.PagingSource
import com.sanam.yavarpour.image_search.data.dto.HitDto
import com.sanam.yavarpour.image_search.data.local.dao.ImagesDao
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalSource
import javax.inject.Inject

class ImageLocalDataSource @Inject constructor(
    private val imageDao: ImagesDao
) : ImageLocalSource {
    override fun getAllImages(): PagingSource<Int, HitDto> = imageDao.getAllImages()

    override suspend fun addImages(images: List<HitDto>) = imageDao.addImages(images)

    override suspend fun deleteAllImages() = imageDao.deleteAllImages()
}