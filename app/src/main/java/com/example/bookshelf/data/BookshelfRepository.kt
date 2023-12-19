package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

// Define una interfaz BookshelfRepository con métodos para obtener información de libros
interface BookshelfRepository {
    // Obtiene una lista de libros basada en una consulta dada
    suspend fun getBooks(query: String): List<Book>?
    // Obtiene información detallada de un libro específico basado en su ID
    suspend fun getBook(id: String): Book?
}

// Implementa la interfaz BookshelfRepository con la clase DefaultBookshelfRepository
class DefaultBookshelfRepository(
    // Inyecta una instancia de BookshelfApiService como dependencia
    private val bookshelfApiService: BookshelfApiService
) : BookshelfRepository {

    // Implementa el método para obtener una lista de libros basada en una consulta
    override suspend fun getBooks(query: String): List<Book>? {
        return try {
            // Realiza la llamada a la API para obtener libros
            val res = bookshelfApiService.getBooks(query)
            // Verifica si la llamada fue exitosa
            if (res.isSuccessful) {
                // Devuelve la lista de libros si está disponible, o una lista vacía si es nula
                res.body()?.items ?: emptyList()
            } else {
                // Devuelve una lista vacía si la llamada no fue exitosa
                emptyList()
            }
        } catch (e: Exception) {
            // Maneja cualquier excepción imprevista, imprime la traza y devuelve nulo
            e.printStackTrace()
            null
        }
    }

    // Implementa el método para obtener información detallada de un libro específico
    override suspend fun getBook(id: String): Book? {
        return try {
            // Realiza la llamada a la API para obtener información de un libro por su ID
            val res = bookshelfApiService.getBook(id)
            // Verifica si la llamada fue exitosa
            if (res.isSuccessful) {
                // Devuelve la información del libro si está disponible, o nulo si es nula
                res.body()
            } else {
                // Devuelve nulo si la llamada no fue exitosa
                null
            }
        } catch (e: Exception) {
            // Maneja cualquier excepción imprevista, imprime la traza y devuelve nulo
            e.printStackTrace()
            null
        }
    }
}
