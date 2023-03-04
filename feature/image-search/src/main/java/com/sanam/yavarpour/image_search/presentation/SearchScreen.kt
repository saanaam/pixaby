package com.sanam.yavarpour.image_search.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sanam.yavarpour.image_search.R
import com.sanam.yavarpour.image_search.presentation.components.EmptyView
import com.sanam.yavarpour.image_search.presentation.components.ErrorAndRetry
import com.sanam.yavarpour.image_search.presentation.components.SearchView
import com.sanam.yavarpour.image_search.presentation.components.imageItem
import com.sanam.yavarpour.ui.util.CircularProgressIndicatorSize
import com.sanam.yavarpour.ui.util.ProgressPadding
import com.sanam.yavarpour.ui.util.SearchListItemsSpace

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel
) {
    val searchedImages = viewModel.hitListPagingData.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()
    val selectedItem by viewModel.selectedItem.collectAsState(initial = null)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchView(
                modifier = Modifier.testTag(stringResource(R.string.test_tag_search_screen_search_view)),
                text = state.searchedValue,
                onTextChange = {
                    viewModel.onEvent(SearchEvent.OnTextChange(it))
                }, onSearchClick = {
                    viewModel.onEvent(SearchEvent.OnSearchClick(it))
                }, onCloseClick = {
                    viewModel.onEvent(SearchEvent.OnCloseClick)
                })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(SearchListItemsSpace)
            ) {
                items(
                    items = searchedImages,
                    key = { hit ->
                        hit.id
                    }
                ) { hit ->
                    hit?.let {
                        imageItem(hit) {
                            viewModel.onEvent(SearchEvent.OnSearchItemClick(hit))
                        }
                    }
                }
                searchedImages.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .padding(ProgressPadding)
                                            .align(Alignment.Center)
                                            .width(CircularProgressIndicatorSize)
                                            .height(CircularProgressIndicatorSize),
                                        color = colorResource(id = R.color.green_500)
                                    )
                                }
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .padding(ProgressPadding)
                                            .align(Alignment.Center)
                                            .width(CircularProgressIndicatorSize)
                                            .height(CircularProgressIndicatorSize),
                                        color = colorResource(id = R.color.green_500)
                                    )
                                }
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val e = searchedImages.loadState.append as LoadState.Error
                            item {
                                e.error.localizedMessage?.let {
                                    ErrorAndRetry(Modifier, it) {
                                        retry()
                                    }
                                } ?: run {
                                    ErrorAndRetry(
                                        Modifier,
                                        stringResource(R.string.search_screen_error_message)
                                    ) {
                                        retry()
                                    }
                                }
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = searchedImages.loadState.refresh as LoadState.Error
                            item {
                                e.error.localizedMessage?.let {
                                    ErrorAndRetry(Modifier, it) {
                                        retry()
                                    }
                                } ?: run {
                                    ErrorAndRetry(
                                        Modifier,
                                        stringResource(R.string.search_screen_error_message)
                                    ) {
                                        retry()
                                    }
                                }
                            }
                        }
                        loadState.append is LoadState.NotLoading && !loadState.append.endOfPaginationReached -> {
                            item {
                                if (searchedImages.itemCount < 1) {
                                    EmptyView(Modifier)
                                }
                            }
                        }
                    }
                }
            }
        }

        val context = LocalContext.current
        selectedItem?.apply {
            Toast.makeText(
                context,
                stringResource(id = R.string.search_screen_search_item_photoby, this.user),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}

