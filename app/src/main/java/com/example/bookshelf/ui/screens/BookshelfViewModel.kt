package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Define una interfaz sellada para representar los estados posibles de la interfaz de usuario del estante de libros
sealed interface BookshelfUiState {
    // Estado de éxito que contiene una lista de libros
    data class Success(val book: List<Book>) : BookshelfUiState
    // Estado de error
    object Error : BookshelfUiState
    // Estado de carga
    object Loading : BookshelfUiState
}

// Clase que representa la ViewModel asociada a la pantalla del estante de libros
class BookshelfViewModel(private val bookshelfRepository: BookshelfRepository) : ViewModel() {

    // Propiedad mutable que representa el estado actual de la interfaz de usuario
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    // Inicializador que se ejecuta al crear una instancia de la ViewModel
    init {
        getBookshelf()
    }

    // Método para obtener la lista de libros del repositorio
    fun getBookshelf() {
        viewModelScope.launch {
            // Inicia el estado como "Cargando"
            bookshelfUiState = BookshelfUiState.Loading
            // Intenta obtener la lista de libros desde el repositorio
            bookshelfUiState = try {
                val books = bookshelfRepository.getBooks("jazz+history")
                // Si la lista de libros no es nula, establece el estado como "Éxito", de lo contrario, establece el estado como "Error"
                if (books != null) {
                    BookshelfUiState.Success(books)
                } else {
                    BookshelfUiState.Error
                }
            } catch (e: IOException) {
                // Maneja excepciones de red o E/S estableciendo el estado como "Error"
                BookshelfUiState.Error
            } catch (e: HttpException) {
                // Maneja excepciones HTTP estableciendo el estado como "Error"
                BookshelfUiState.Error
            }
        }
    }

    // Compañero de objetos que proporciona un Factory para crear instancias de la ViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Obtiene la instancia de la aplicación para acceder al contenedor de dependencias
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as BookshelfApplication)
                // Obtiene el repositorio del contenedor y crea la ViewModel
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}
