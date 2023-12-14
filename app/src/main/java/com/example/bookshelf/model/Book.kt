package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
) {
    fun getTitle(): String = volumeInfo.title
    fun getThumbnailUrl(): String? = volumeInfo.imageLinks?.thumbnail
}

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null,
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
) {
    val httpsThumbnail: String
        get() = thumbnail.replace("http", "https")
}
