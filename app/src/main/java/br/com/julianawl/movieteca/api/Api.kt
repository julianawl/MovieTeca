package br.com.julianawl.movieteca.api

import br.com.julianawl.movieteca.data.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movies/{category}")
    fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
    ): Call<GetMoviesResponse>

}