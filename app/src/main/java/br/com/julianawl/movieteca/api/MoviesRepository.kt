package br.com.julianawl.movieteca.api

import br.com.julianawl.movieteca.Category
import br.com.julianawl.movieteca.data.GetMoviesResponse
import br.com.julianawl.movieteca.data.Movie
import br.com.julianawl.movieteca.database.MovieDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(
    private val dao: MovieDAO,
    private val api: Api
) {

    fun getMovies(
        category: Category,
        page: Int,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit){
        api.getMovies(category = category, page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


}