package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.theme.BookshelfTheme

// Función de composición para la pantalla principal (HomeScreen)
@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is BookshelfUiState.Success ->
            BookshelfListScreen(
                books = bookshelfUiState.book,
                modifier = modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                contentPadding = contentPadding
            )
        else -> ErrorScreen(retryAction, modifier)
    }
}

// Función de composición para la pantalla de carga (LoadingScreen)
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

// Función de composición para la pantalla de error (ErrorScreen)
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

// Función de composición para la tarjeta de un libro (BookshelfCard)
@Composable
fun BookshelfCard(book: Book, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Título del libro
            Text(
                text = book.getTitle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            // Autores del libro (si están disponibles)
            book.getAuthors()?.let { authors ->
                Text(
                    text = authors.joinToString(", "),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start
                )
            }
            // Imagen del libro cargada de forma asíncrona con Coil
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.getThumbnailUrl() ?: "")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
        }
    }
}

// Función de composición para la pantalla de la lista de libros (BookshelfListScreen)
@Composable
private fun BookshelfListScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(
            items = books.chunked(2),
            key = { bookPair ->
                bookPair.firstOrNull()?.id ?: ""
            }
        ) { bookPair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (book in bookPair) {
                    // Muestra las tarjetas de los libros en una fila
                    BookshelfCard(book = book, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

// Previsualización de la pantalla de carga
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    BookshelfTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

// Previsualización de la pantalla de error
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    BookshelfTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

// Previsualización de la lista de libros (vacía por ahora)
@Preview(showBackground = true)
@Composable
fun BookshelfListScreenPreview() {
    BookshelfTheme {

    }
}
