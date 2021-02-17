package br.com.julianawl.movieteca.api

import androidx.lifecycle.LiveData
import br.com.julianawl.movieteca.Category
import br.com.julianawl.movieteca.data.GetMoviesResponse
import br.com.julianawl.movieteca.data.Movie
import br.com.julianawl.movieteca.database.MovieDAO
import br.com.julianawl.movieteca.ui.MainActivity
import br.com.julianawl.movieteca.ui.fragments.MoviesListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(
    private val dao: MovieDAO,
    private val api: Api
) {

    private val onError = MoviesListFragment().onError()

    fun getMovies(
        category: String,
        page: Int = 1,
        ) :LiveData<List<Movie>>{
        api.getMovies(category = category, page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            MoviesListFragment().onSuccess(category,
                                responseBody.movies)

                        } else {
                            onError
                        }
                    } else {
                        onError
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError
                }

            })
        return dao.getMovies(category,page)
    }


}