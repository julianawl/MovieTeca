package br.com.julianawl.movieteca.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.julianawl.movieteca.Category
import br.com.julianawl.movieteca.R
import br.com.julianawl.movieteca.data.Movie
import br.com.julianawl.movieteca.ui.MoviesAdapter
import br.com.julianawl.movieteca.ui.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesListFragment : Fragment() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutManager: LinearLayoutManager
    private var popularMoviesPage = 1

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMoviesLayoutManager: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var upcomingMoviesLayoutManager: LinearLayoutManager
    private var upcomingMoviesPage = 1

    var whenMovieSelected: (movie: Movie) -> Unit = {}
    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.activity_main,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurePopular()
        configureTopRated()
        configureUpcoming()
    }

    private fun configurePopular() {
        R.id.popular_movies_list
        popularMoviesLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMovies.layoutManager = popularMoviesLayoutManager
        configuraAdapter(popularMoviesAdapter, popularMovies)
    }

    private fun configureTopRated() {
        R.id.top_rated_list
        topRatedMoviesLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        configuraAdapter(topRatedMoviesAdapter, topRatedMovies)
    }

    private fun configureUpcoming() {
        R.id.upcoming_list
        upcomingMoviesLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        configuraAdapter(upcomingMoviesAdapter, upcomingMovies)
    }

    private fun configuraAdapter(
        adapter: MoviesAdapter,
        movies: RecyclerView
    ) {
        adapter.onMovieClick = whenMovieSelected
        movies.adapter = adapter
    }

    private fun getPopularMovies() {
        viewModel.getMovies(
            Category.POPULAR,
            popularMoviesPage).observe(this, Observer {
                it?.let {
                    popularMoviesAdapter.appendMovies(it)
                    attachMoviesOnScrollListener(
                        popularMoviesLayoutManager,
                        popularMovies,
                        popularMoviesPage,
                        Category.POPULAR
                    )
                }
        })
    }

    private fun getTopRatedMovies() {
        viewModel.getMovies(
            Category.TOP_RATED.toString(),
            topRatedMoviesPage).observe(this, Observer {
            it?.let {
                topRatedMoviesAdapter.appendMovies(it)
            }
        })
    }

    private fun getUpcomingMovies() {
        viewModel.getMovies(
            Category.UPCOMING.toString(),
            upcomingMoviesPage).observe(this, Observer {
            it?.let {
                upcomingMoviesAdapter.appendMovies(it)
            }
        })
    }

    fun onError(){
        Toast.makeText(context,
            getString(R.string.error_fetch_movies),
            Toast.LENGTH_SHORT).show()
    }

    private fun onPopularMoviesFetched(listMovies: List<Movie>) {
        popularMoviesAdapter.appendMovies(listMovies)

    }

    private fun attachMoviesOnScrollListener(
        layoutManager: LinearLayoutManager,
        movies: RecyclerView,
        pages: Int,
        category: Category
    ) {
        movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    movies.removeOnScrollListener(this)
                    pages + 1
                    when (category) {
                        Category.POPULAR -> getPopularMovies()
                        Category.TOP_RATED -> getTopRatedMovies()
                        Category.UPCOMING -> getUpcomingMovies()
                    }
                }
            }
        })
    }

}