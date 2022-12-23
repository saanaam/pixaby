package com.sanam.yavarpour.image_search.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanam.yavarpour.image_search.data.util.Constants.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class ImagesRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)