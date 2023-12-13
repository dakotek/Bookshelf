package com.example.bookshelf.network

import com.example.bookshelf.model.Bookshelf
import retrofit2.http.GET

interface BookshelfApiService {
    @GET("amphibians")
    suspend fun getBookshelf(): List<Bookshelf>
}
