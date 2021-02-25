package br.com.julianawl.movieteca.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.julianawl.movieteca.MoviesCategory
import br.com.julianawl.movieteca.R
import br.com.julianawl.movieteca.data.Movie
import br.com.julianawl.movieteca.ui.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


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

    private val viewModel: MoviesViewModel by viewModel()

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
            popularMoviesPage
        )
        viewModel.items.observe(this, Observer {
            movies -> movies?.let {
                popularMoviesAdapter.appendMovies(it)
                attachPopularMoviesOnScrollListener()
            }
        })
    }

    private fun getTopRatedMovies() {
        viewModel.getMovies(
            MoviesCategory.TOP_RATED.path,
            topRatedMoviesPage
        )
        viewModel.items.observe(this, Observer {
            movies -> movies?.let {
                topRatedMoviesAdapter.appendMovies(it)
                attachTopRatedMoviesOnScrollListener()
            }
        })
    }

    private fun getUpcomingMovies() {
        viewModel.getMovies(
            MoviesCategory.UPCOMING.path,
            upcomingMoviesPage
        )
        viewModel.items.observe(this, Observer {
            movies -> movies?.let {
                upcomingMoviesAdapter.appendMovies(it)
                attachUpcomingMoviesOnScrollListener()
            }
        })
    }

    private fun attachPopularMoviesOnScrollListener() {
        popular_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popular_movies.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

    private fun attachTopRatedMoviesOnScrollListener() {
        top_rated_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    top_rated_movies.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    getTopRatedMovies()
                }
            }
        })
    }

    private fun attachUpcomingMoviesOnScrollListener() {
        upcoming_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upcomingMoviesLayoutMgr.itemCount
                val visibleItemCount = upcomingMoviesLayoutMgr.childCount
                val firstVisibleItem = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcoming_movies.removeOnScrollListener(this)
                    upcomingMoviesPage++
                    getUpcomingMovies()
                }
            }
        })
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