package com.sanam.yavarpour.image_search.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanam.yavarpour.core.model.RemoteKey
import com.sanam.yavarpour.image_search.data.util.Constants.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class ImagesRemoteKeys(
    @PrimaryKey
    override val id: String,
    override val prevPage: Int?,
    override val nextPage: Int?
) : RemoteKey