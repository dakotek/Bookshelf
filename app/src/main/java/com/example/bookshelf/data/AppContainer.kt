package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// Define una interfaz para contener las dependencias de la aplicación
interface AppContainer {
    // Proporcionan instancias de BookshelfApiService y BookshelfRepository
    val bookshelfApiService: BookshelfApiService
    val bookshelfRepository: BookshelfRepository
}

// Implementa la interfaz AppContainer con una implementación predeterminada
class DefaultAppContainer : AppContainer {
    // Utiliza delegación perezosa para crear una instancia única de BookshelfApiService cuando sea necesario
    override val bookshelfApiService: BookshelfApiService by lazy {
        // Configura Retrofit para construir una instancia de BookshelfApiService
        Retrofit.Builder()
            // Agrega el convertidor Gson para manejar la serialización y deserialización de objetos JSON
            .addConverterFactory(GsonConverterFactory.create())
            // Especifica la URL base de la API del estante de libros
            .baseUrl(BookshelfApiService.BASE_URL)
            // Construye la instancia de Retrofit
            .build()
            // Crea una instancia de BookshelfApiService utilizando Retrofit
            .create()
    }

    // Utiliza delegación perezosa para crear una instancia única de DefaultBookshelfRepository cuando sea necesario
    override val bookshelfRepository: BookshelfRepository by lazy {
        // Crea una instancia de DefaultBookshelfRepository pasando el BookshelfApiService como dependencia
        DefaultBookshelfRepository(bookshelfApiService)
    }
}
