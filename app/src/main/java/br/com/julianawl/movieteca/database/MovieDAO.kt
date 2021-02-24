package br.com.julianawl.movieteca.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.julianawl.movieteca.data.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM Movie")
    fun getMovies(category: String, page: Int): LiveData<List<Movie>>

    @Insert
    fun update(): LiveData<List<Movie>>
}