package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
) {
    fun getTitle(): String = volumeInfo.title
    fun getAuthors(): List<String>? = volumeInfo.authors
    fun getThumbnailUrl(): String? = volumeInfo.imageLinks?.httpsThumbnail
}

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>? = null,
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
) {
    val httpsThumbnail: String
        get() = thumbnail.replace("http", "https")
}
