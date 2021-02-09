package br.com.julianawl.movieteca.api

import br.com.julianawl.movieteca.data.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "1394c9c9e07ab42ee324ccf9b8387343",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "1394c9c9e07ab42ee324ccf9b8387343",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "1394c9c9e07ab42ee324ccf9b8387343",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}