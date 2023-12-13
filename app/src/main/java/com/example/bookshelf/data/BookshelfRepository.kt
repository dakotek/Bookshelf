package com.example.bookshelf.data

import com.example.bookshelf.model.Bookshelf
import com.example.bookshelf.network.BookshelfApiService

/**
 * Repository retrieves bookshelf data from underlying data source.
 */
interface BookshelfRepository {
    /** Retrieves list of bookshelf from underlying data source */
    suspend fun getBookshelf(): List<Bookshelf>
}

/**
 * Network Implementation of repository that retrieves bookshelf data from underlying data source.
 */
class DefaultBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookshelfRepository {
    /** Retrieves list of bookshelf from underlying data source */
    override suspend fun getBookshelf(): List<Bookshelf> = bookshelfApiService.getBookshelf()
}
