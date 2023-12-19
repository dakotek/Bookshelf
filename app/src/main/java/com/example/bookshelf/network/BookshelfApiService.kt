package com.example.bookshelf.network

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.QueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Define la interfaz BookshelfApiService que describe las operaciones disponibles en la API del estante de libros
interface BookshelfApiService {

    // Define la URL base para las solicitudes a la API del estante de libros
    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }

    // Método para obtener una lista de libros basada en una consulta dada
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): Response<QueryResponse>

    // Método para obtener información detallada de un libro específico basado en su ID
    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Response<Book>
}