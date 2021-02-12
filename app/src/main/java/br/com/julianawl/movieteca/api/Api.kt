package br.com.julianawl.movieteca.api

import br.com.julianawl.movieteca.data.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movies/{category}")
    fun getPopularMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = "1394c9c9e07ab42ee324ccf9b8387343",
        @Query("page") page: Int,
    ): Call<GetMoviesResponse>

}