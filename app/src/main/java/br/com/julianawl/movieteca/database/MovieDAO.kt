package br.com.julianawl.movieteca.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.julianawl.movieteca.Category
import br.com.julianawl.movieteca.data.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM Movie ORDER BY releaseDate DESC")
    fun getMovies(
        category: Category,
        page: Int
    ): LiveData<List<Movie>>
}