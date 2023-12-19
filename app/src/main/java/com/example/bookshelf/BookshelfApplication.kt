package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.AppContainer
import com.example.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {
    // Propiedad que representa el contenedor de dependencias para la inyección de objetos
    lateinit var container: AppContainer

    // Método llamado al crear la aplicación
    override fun onCreate() {
        super.onCreate()
        // Inicializa el contenedor de dependencias con la implementación por defecto
        container = DefaultAppContainer()
    }
}
