package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp() {
    // Configuración del diseño de la aplicación
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Barra superior de la aplicación con el nombre
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Obtiene la ViewModel asociada a la pantalla principal (HomeScreen)
            val bookshelfViewModel: BookshelfViewModel =
                viewModel(factory = BookshelfViewModel.Factory)
            // Muestra la pantalla principal
            HomeScreen(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                retryAction = bookshelfViewModel::getBookshelf,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}
