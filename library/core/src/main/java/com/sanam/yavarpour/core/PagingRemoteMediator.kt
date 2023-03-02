package com.sanam.yavarpour.core

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.sanam.yavarpour.core.model.RemoteKey

@OptIn(ExperimentalPagingApi::class)
abstract class PagingRemoteMediator<Value : Any> constructor(
    vararg params: Any
) : RemoteMediator<Int, Value>() {

    private val requestParams = params

    /**
     * load method fetches the new data from a network source and saves it to local storage
     * loadType gives us information about the pages,load data at the end (LoadType.APPEND)
     * or at the beginning of the data (LoadType.PREPEND) that we previously loaded
     * or if this the first time we’re loading data (LoadType.REFRESH).
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Value>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                //it’s the first time we’re loading data
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                //need to load data at the beginning of the currently loaded data set
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                //need to load data at the end of the currently loaded data set
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            Log.e("currentPage : " , currentPage.toString())
            getDataFromRemoteDataSource(requestParams, page = currentPage).let { response ->
                if (response.valueOrNull != null) {
                    val endOfPaginationReached = response.valueOrNull == null
                    val prevPage = if (currentPage == 1) null else currentPage - 1
                    val nextPage = if (endOfPaginationReached) null else currentPage + 1
                    if (loadType == LoadType.REFRESH) {
                        clearLocalDataSource()
                    }
                    response.valueOrNull?.also {
                        saveToDatabase(it, prevPage, nextPage)
                    }
                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                } else {
                    return MediatorResult.Error(Throwable(response.exception))
                }
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    abstract suspend fun getDataFromRemoteDataSource(
        vararg params: Any, page: Int
    ): Resource<List<Value>?>

    abstract suspend fun saveToDatabase(
        value: List<Value>,
        prevPage: Int?,
        nextPage: Int?
    )

    /**
     * getRemoteKeyClosestToCurrentPosition, based on anchorPosition from the state,
     * get the closest item to that position by calling closestItemToPosition and retrieve RemoteKeys from database.
     * If RemoteKeys is null, we return the first page number which is 1
     */
    abstract suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Value>
    ): RemoteKey?

    /**
     * get the first Movie item loaded from the database
     */
    abstract suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Value>
    ): RemoteKey?

    /**
     * get the last item loaded from the database.
     */
    abstract suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Value>
    ): RemoteKey?

    abstract suspend fun clearLocalDataSource()
}