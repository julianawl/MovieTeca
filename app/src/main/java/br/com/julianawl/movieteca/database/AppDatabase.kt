package br.com.julianawl.movieteca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.julianawl.movieteca.data.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDAO

}