package br.com.julianawl.movieteca.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.julianawl.movieteca.MoviesCategory
import br.com.julianawl.movieteca.R
import br.com.julianawl.movieteca.data.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1

    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    private var upcomingMoviesPage = 1

    private val viewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraPopularMovies()
        configuraTopRatedMovies()
        configuraUpcomingMovies()

        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun configuraPopularMovies() {
        popularMoviesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popular_movies.layoutManager = popularMoviesLayoutMgr

        popularMoviesAdapter =
            MoviesAdapter(mutableListOf()) { movie ->
                showMovieDetails(movie)
            }
        popular_movies.adapter = popularMoviesAdapter
    }

    private fun configuraTopRatedMovies() {
        topRatedMoviesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        top_rated_movies.layoutManager = topRatedMoviesLayoutMgr

        topRatedMoviesAdapter =
            MoviesAdapter(mutableListOf()) { movie ->
                showMovieDetails(movie)
            }
        top_rated_movies.adapter = topRatedMoviesAdapter
    }

    private fun configuraUpcomingMovies() {
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcoming_movies.layoutManager = upcomingMoviesLayoutMgr

        upcomingMoviesAdapter =
            MoviesAdapter(mutableListOf()) { movie ->
                showMovieDetails(movie)
            }
        upcoming_movies.adapter = upcomingMoviesAdapter
    }

    private fun getPopularMovies() {
        viewModel.getMovies(
            MoviesCategory.POPULAR.path,
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }



    private fun getTopRatedMovies() {
        viewModel.getMovies(
            MoviesCategory.TOP_RATED.path,
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }



    private fun getUpcomingMovies() {
        viewModel.getMovies(
            MoviesCategory.UPCOMING.path,
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }

    private fun onError() {
        Toast.makeText(this,
            getString(R.string.error_fetch_movies),
            Toast.LENGTH_SHORT).show()
    }

}