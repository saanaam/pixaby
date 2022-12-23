package com.sanam.yavarpour.image_search.data.dto

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ImageSearchResponse(
    @SerializedName("hits")
    val hits: List<HitDto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)