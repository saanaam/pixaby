package com.sanam.yavarpour.image_search.data.repositoty.mapper

import com.sanam.yavarpour.core.model.Hit
import com.sanam.yavarpour.image_search.data.dto.HitDto

fun HitDto.toHit(): Hit = Hit(
    id = this.id,
    user = this.user,
    largeImageURL = this.previewURL,
    webFormatURL = this.webFormatURL,
    tags = this.tags,
    likes = this.likes,
    downloads = this.downloads,
    imageHeight = this.imageHeight,
    imageSize = this.imageSize,
    imageWidth = this.imageWidth,
)
