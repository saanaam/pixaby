package com.sanam.yavarpour.core.model

data class Hit(
    val id: String = "",
    val user: String = "",
    val largeImageURL: String = "",
    val webFormatURL: String = "",
    val tags: String = "",
    val likes: Int = 0,
    val downloads: Int = 0,
    val comments: Int = 0,
    val imageHeight: Int = 0,
    val imageSize: Int = 0,
    val imageWidth: Int = 0,
)