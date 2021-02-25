package br.com.julianawl.movieteca.di

import androidx.room.Room
import br.com.julianawl.movieteca.api.AppRetrofit
import br.com.julianawl.movieteca.api.MoviesRepository
import br.com.julianawl.movieteca.database.AppDatabase
import br.com.julianawl.movieteca.database.MovieDAO
import br.com.julianawl.movieteca.ui.MainActivity
import br.com.julianawl.movieteca.ui.MovieDetailsActivity
import br.com.julianawl.movieteca.ui.MoviesAdapter
import br.com.julianawl.movieteca.ui.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "movies.db"

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }

}

val daoModule = module {
    single<MovieDAO> { get<AppDatabase>().movieDao }

}

val repositoryModule = module{
    single<MoviesRepository> { MoviesRepository(get(), get()) }
}

val uiModule = module {
    factory<MoviesAdapter> { MoviesAdapter(get(),get()) }
}

val viewModelModule = module {
    viewModel<MoviesViewModel> { MoviesViewModel(get()) }
}

val apiModule = module {
    single<AppRetrofit> { AppRetrofit() }
}

val listOfModules = listOf(
    uiModule,
    viewModelModule,
    apiModule,
    repositoryModule,
    appModule,
    daoModule
)