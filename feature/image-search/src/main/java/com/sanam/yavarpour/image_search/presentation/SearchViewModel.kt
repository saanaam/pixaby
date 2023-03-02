package com.sanam.yavarpour.image_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sanam.yavarpour.core.model.Hit
import com.sanam.yavarpour.image_search.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
) : ViewModel() {
    private var _hitListPagingData = MutableStateFlow<PagingData<Hit>>(PagingData.empty())
    val hitListPagingData = _hitListPagingData.asStateFlow()
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()
    private val _selectedItem = MutableSharedFlow<Hit?>()
    val selectedItem = _selectedItem.asSharedFlow()

    init {
        searchImage("All")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnTextChange -> {
                _state.value = state.value.copy(searchedValue = event.text)
                searchImage()
            }
            is SearchEvent.OnSearchClick -> {
                _state.value = state.value.copy(searchedValue = event.text)
                searchImage()
            }
            is SearchEvent.OnCloseClick -> {
                _state.value = state.value.copy(searchedValue = "All")
            }
            is SearchEvent.OnSearchItemClick -> {
                viewModelScope.launch {
                    _selectedItem.emit(event.hit)
                }
            }
        }
    }

    private fun searchImage(query: String = state.value.searchedValue) {
        if (query.length >= 3) {
            viewModelScope.launch {
                searchImageUseCase(query = query).cachedIn(viewModelScope).collect {
                    _hitListPagingData.value = it
                }
            }
        }
    }
}