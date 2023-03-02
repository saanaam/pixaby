package com.sanam.yavarpour.image_search.presentation

import com.sanam.yavarpour.core.model.Hit


sealed class SearchEvent {
    data class OnTextChange(val text: String) : SearchEvent()
    data class OnSearchClick(val text: String) : SearchEvent()
    data class OnSearchItemClick(val hit: Hit) : SearchEvent()
    object OnCloseClick : SearchEvent()
}
