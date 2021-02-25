package br.com.julianawl.movieteca.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.julianawl.movieteca.api.Resource
import br.com.julianawl.movieteca.data.GetMoviesResponse
import br.com.julianawl.movieteca.data.Movie
import br.com.julianawl.movieteca.database.MovieDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(
    private val dao: MovieDAO,
    private val api: Api = AppRetrofit().movieService
) {
    private val _items = MutableLiveData<List<Movie>>()
    val items : LiveData<List<Movie>>
    get() = _items

    suspend fun getMovies(
        category: String,
        page: Int = 1
    ) :LiveData<List<Movie>>{
            val movies = api.getMovies(category,page)
            if(movies.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = movies.body()
                }
            }
        return items
    }

}