package com.sanam.yavarpour.image_search.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys

@Dao
interface ImagesRemoteKeysDao {

    @Query("SELECT * FROM pixabay_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): ImagesRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ImagesRemoteKeys>)

    @Query("DELETE FROM pixabay_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}