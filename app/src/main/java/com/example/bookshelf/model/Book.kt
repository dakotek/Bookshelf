package com.example.bookshelf.model

import kotlinx.serialization.Serializable

// Define la clase de datos Book, marcada como serializable
@Serializable
data class Book(
    // Propiedades de datos de un libro
    val id: String,
    val volumeInfo: VolumeInfo,
) {
    // Método para obtener el título del libro
    fun getTitle(): String = volumeInfo.title

    // Método para obtener la lista de autores del libro
    fun getAuthors(): List<String>? = volumeInfo.authors

    // Método para obtener la URL de la miniatura del libro
    fun getThumbnailUrl(): String? = volumeInfo.imageLinks?.httpsThumbnail
}

// Define la clase de datos VolumeInfo, marcada como serializable
@Serializable
data class VolumeInfo(
    // Propiedades de datos relacionadas con la información del volumen de un libro
    val title: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>? = null,
)

// Define la clase de datos ImageLinks, marcada como serializable
@Serializable
data class ImageLinks(
    // Propiedades de datos relacionadas con los enlaces de imágenes de un libro
    val smallThumbnail: String,
    val thumbnail: String,
) {
    // Propiedad calculada para obtener la URL de la miniatura con protocolo HTTPS
    val httpsThumbnail: String
        get() = thumbnail.replace("http", "https")
}
