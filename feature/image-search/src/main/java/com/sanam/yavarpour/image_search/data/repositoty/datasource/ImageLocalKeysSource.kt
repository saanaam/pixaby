package com.sanam.yavarpour.image_search.data.repositoty.datasource

import com.sanam.yavarpour.core.model.LocalSource
import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys

interface ImageLocalKeysSource : LocalSource {
    suspend fun getRemoteKeys(id: String): ImagesRemoteKeys
    suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKeys>)
    suspend fun deleteAllRemoteKeys()
}