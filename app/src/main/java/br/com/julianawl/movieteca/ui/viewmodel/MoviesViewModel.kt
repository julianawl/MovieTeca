package br.com.julianawl.movieteca.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import br.com.julianawl.movieteca.api.MoviesRepository
import br.com.julianawl.movieteca.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository
) : ViewModel(){

    lateinit var items : LiveData<List<Movie>>

    fun getMovies(
        category: String,
        page: Int)
    {
        viewModelScope.launch(Dispatchers.IO){
            items = repository.getMovies(category, page)
        }

    }

}