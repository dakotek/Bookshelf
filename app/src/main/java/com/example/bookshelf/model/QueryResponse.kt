package com.example.bookshelf.model

import kotlinx.serialization.Serializable

// Define la clase de datos QueryResponse, marcada como serializable
@Serializable
data class QueryResponse(
    // Lista de libros en respuesta a una consulta
    val items: List<Book>?,

    // Número total de libros disponibles en la respuesta
    val totalItems: Int,

    // Tipo de respuesta, como "books#volumes"
    val kind: String,
)