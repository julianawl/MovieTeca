package br.com.julianawl.movieteca.api

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

    private val mediador = MediatorLiveData<Resource<List<Movie>?>>()

    fun getMovies(
        category: String,
        page: Int): MediatorLiveData<Resource<List<Movie>?>> {

        mediador.addSource(dao.getMovies(category, page)){
            mediador.value = Resource(dado = it)
        }

        val fails = MutableLiveData<Resource<List<Movie>?>>()
        mediador.addSource(fails){ failResource ->
            val resourceAtual = mediador.value
            val newResource : Resource<List<Movie>?> =
                if (resourceAtual != null){
                    Resource(dado = resourceAtual.dado, erro = failResource.erro)
                }else{
                    failResource
                }
            mediador.value = newResource
        }

        api.getMovies(category = category, page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()
                        responseBody?.let {

                        }
                    } else {
                        
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    //onError.invoke()
                }
            })
        return mediador
    }

}