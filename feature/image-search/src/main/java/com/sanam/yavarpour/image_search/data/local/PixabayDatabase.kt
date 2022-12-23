package com.sanam.yavarpour.image_search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanam.yavarpour.image_search.data.dto.HitDto
import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys
import com.sanam.yavarpour.image_search.data.local.dao.ImagesDao
import com.sanam.yavarpour.image_search.data.local.dao.ImagesRemoteKeysDao

@Database(entities = [HitDto::class, ImagesRemoteKeys::class], version = 1)
abstract class PixabayDatabase : RoomDatabase(){
    abstract fun imagesDao(): ImagesDao
    abstract fun imagesRemoteKeysDao(): ImagesRemoteKeysDao
}