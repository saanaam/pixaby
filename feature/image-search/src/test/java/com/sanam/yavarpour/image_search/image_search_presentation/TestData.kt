package com.sanam.yavarpour.image_search.image_search_presentation

import com.sanam.yavarpour.core.model.Hit


object TestData {

    val hit1 = Hit(
        id = "13444",
        user = "user_one",
        largeImageURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-729509_150.jpg",
        webFormatURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-729509_122.jpg",
        tags = "girl",
        likes = 131,
        downloads = 44133,
        comments = 87777
    )
    val hit2 = Hit(
        id = "13413",
        user = "jake",
        largeImageURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-1341341341.jpg",
        webFormatURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-111233223.jpg",
        tags = "dog",
        likes = 131,
        downloads = 44133,
        comments = 87777
    )
    val hit3 = Hit(
        id = "87661",
        user = "pitter",
        largeImageURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-afadfadfad.jpg",
        webFormatURL = "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-729509_300.jpg",
        tags = "boy",
        likes = 131,
        downloads = 44133,
        comments = 87777
    )

    val hitList = listOf(hit1, hit2, hit3)

    val sampleTestQuery1 = "dog"
    val sampleTestQuery2 = "boy"
    val sampleTestQuery3 = "yo"
}