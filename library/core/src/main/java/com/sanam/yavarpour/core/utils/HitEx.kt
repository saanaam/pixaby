package com.sanam.yavarpour.core.utils

fun String.tagsToArray(): List<String> {
    return this.split(",")
}