package com.sanam.yavarpour.image_search.data.util

fun String.toPixabayQuery():String{
   return this.replace(" ","+")
}