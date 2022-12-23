package com.sanam.yavarpour.core

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
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Value>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = getDataFromRemoteDataSource(requestParams, page = currentPage)
            if (!response.isFail) {
                val endOfPaginationReached = response.valueOrNull == null
                val prevPage = if (currentPage == 1) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1
                if (loadType == LoadType.REFRESH) {
                    clearLocalDataSource()
                }
                response.valueOrNull?.takeIf { it.isNotEmpty() }?.also {
                    saveToDatabase(it, prevPage, nextPage)
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                return MediatorResult.Error(Throwable(response.exception))
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

    abstract suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Value>
    ): RemoteKey?

    abstract suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Value>
    ): RemoteKey?

    abstract suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Value>
    ): RemoteKey?

    abstract suspend fun clearLocalDataSource()
}