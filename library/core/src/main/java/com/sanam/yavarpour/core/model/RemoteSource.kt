package com.sanam.yavarpour.core.model

interface RemoteSource {
}

interface LocalSource {
}

interface RemoteKey{
    val id: String
    val prevPage: Int?
    val nextPage: Int?
}