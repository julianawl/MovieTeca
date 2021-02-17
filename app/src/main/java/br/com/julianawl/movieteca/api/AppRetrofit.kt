package br.com.julianawl.movieteca.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

class AppRetrofit {

    val client = OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val movieService: Api by lazy {
        retrofit.create(Api::class.java)
    }

}