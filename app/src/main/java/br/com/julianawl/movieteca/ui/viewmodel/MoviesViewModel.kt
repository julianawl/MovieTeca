package br.com.julianawl.movieteca.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.julianawl.movieteca.Category
import br.com.julianawl.movieteca.api.MoviesRepository
import br.com.julianawl.movieteca.data.Movie
import java.util.*

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel(){

    fun getMovies(
        category: Category,
        page: Int): LiveData<List<Movie>> = repository.getMovies(category, page)
}