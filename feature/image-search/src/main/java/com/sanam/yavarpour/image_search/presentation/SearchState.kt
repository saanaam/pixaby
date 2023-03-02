package com.sanam.yavarpour.image_search.presentation

import com.sanam.yavarpour.core.model.Hit

data class SearchState(
    val searchedValue: String = "All",
    val selectedHit: Hit? = null
)
