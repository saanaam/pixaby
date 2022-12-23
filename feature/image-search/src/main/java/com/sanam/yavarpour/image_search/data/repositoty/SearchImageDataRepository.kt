package com.sanam.yavarpour.image_search.data.repositoty

import androidx.paging.*
import com.sanam.yavarpour.core.PagingRemoteMediator
import com.sanam.yavarpour.core.Resource
import com.sanam.yavarpour.core.model.RemoteKey
import com.sanam.yavarpour.image_search.data.dto.HitDto
import com.sanam.yavarpour.image_search.data.dto.ImagesRemoteKeys
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalKeysSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.ImageLocalSource
import com.sanam.yavarpour.image_search.data.repositoty.datasource.SearchImageRemoteSource
import com.sanam.yavarpour.image_search.data.util.Constants.ITEMS_PER_PAGE
import com.sanam.yavarpour.image_search.data.util.toPixabayQuery
import com.sanam.yavarpour.image_search.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchImageDataRepository @Inject constructor(
    private val searchImageRemoteSource: SearchImageRemoteSource,
    private val imageLocalSource: ImageLocalSource,
    private val imageLocalKeysSource: ImageLocalKeysSource
) : SearchImageRepository {
    override suspend fun searchImage(query: String): Flow<PagingData<HitDto>> {
        val pagingSourceFactory = { imageLocalSource.getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = SearchImageRemoteMediator(
                searchImageRemoteSource,
                imageLocalSource,
                imageLocalKeysSource,
                query.toPixabayQuery(),
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}

class SearchImageRemoteMediator(
    private val searchApi: SearchImageRemoteSource,
    private val imageLocalSource: ImageLocalSource,
    private val imageLocalKeysSource: ImageLocalKeysSource,
    private val query: String
) : PagingRemoteMediator<HitDto>() {
    override suspend fun getDataFromRemoteDataSource(
        vararg params: Any,
        page: Int
    ): Resource<List<HitDto>?> {
        val response = searchApi.searchImage(query = query, page)
        return if (response.isFail) {
            Resource.Error(response.exception)
        } else {
            Resource.Success(response.valueOrNull?.hits)
        }
    }

    override suspend fun saveToDatabase(value: List<HitDto>, prevPage: Int?, nextPage: Int?) {
        imageLocalSource.addImages(images = value)
        value.map {
            ImagesRemoteKeys(
                id = it.id,
                prevPage = prevPage,
                nextPage = nextPage
            )
        }.also {
            imageLocalKeysSource.addAllRemoteKeys(remoteKeys = it)
        }
    }

    override suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HitDto>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                imageLocalKeysSource.getRemoteKeys(id = id)
            }
        }
    }

    override suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HitDto>
    ): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pixabayImage ->
                imageLocalKeysSource.getRemoteKeys(id = pixabayImage.id)
            }
    }

    override suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HitDto>
    ): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pixabayImage ->
                imageLocalKeysSource.getRemoteKeys(id = pixabayImage.id)
            }
    }

    override suspend fun clearLocalDataSource() {
        imageLocalSource.deleteAllImages()
        imageLocalKeysSource.deleteAllRemoteKeys()
    }
}