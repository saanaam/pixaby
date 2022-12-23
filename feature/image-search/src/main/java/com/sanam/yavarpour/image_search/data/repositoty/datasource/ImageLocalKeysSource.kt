package com.sanam.yavarpour.image_search.data.repositoty.datasource

import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys

interface ImageLocalKeysSource {
    suspend fun getRemoteKeys(id: String): ImagesRemoteKeys
    suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKeys>)
    suspend fun deleteAllRemoteKeys()
}