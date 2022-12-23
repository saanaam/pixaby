package com.sanam.yavarpour.image_search.data.local.datasource

import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys
import com.sanam.yavarpour.image_search.data.local.dao.ImagesRemoteKeysDao
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalKeysSource
import javax.inject.Inject

class ImageLocalKeysDataSource @Inject constructor(
    private val imagesRemoteKeysDao: ImagesRemoteKeysDao
) : ImageLocalKeysSource {
    override suspend fun getRemoteKeys(id: String): ImagesRemoteKeys =
        imagesRemoteKeysDao.getRemoteKeys(id)
    override suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKeys>) =
        imagesRemoteKeysDao.addAllRemoteKeys(remoteKeys)
    override suspend fun deleteAllRemoteKeys() = imagesRemoteKeysDao.deleteAllRemoteKeys()
}