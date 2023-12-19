package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.BookshelfApp
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    // Método llamado al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el contenido de la actividad con la interfaz de usuario Compose
        setContent {
            // Configura el tema de la aplicación con BookshelfTheme
            BookshelfTheme {
                // Superficie principal que abarca toda la pantalla y establece el fondo
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Lanza la aplicación principal (BookshelfApp)
                    BookshelfApp()
                }
            }
        }
    }
}
