package br.com.julianawl.movieteca.di

import androidx.room.Room
import br.com.julianawl.movieteca.api.MoviesRepository
import br.com.julianawl.movieteca.database.AppDatabase
import br.com.julianawl.movieteca.database.MovieDAO
import br.com.julianawl.movieteca.ui.MainActivity
import br.com.julianawl.movieteca.ui.MoviesAdapter
import br.com.julianawl.movieteca.ui.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "movies.db"

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
    single<MovieDAO> { get<AppDatabase>().movieDao() }
    single<MoviesRepository> { MoviesRepository(get(), get()) }
}

val uiModule = module {
    factory<MainActivity> { MainActivity() }
    factory<MoviesAdapter> { MoviesAdapter(get(),get()) }
}

val viewModelModule = module {
    viewModel<MoviesViewModel> { MoviesViewModel(get()) }
}