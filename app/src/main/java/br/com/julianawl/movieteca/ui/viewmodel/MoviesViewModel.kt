package br.com.julianawl.movieteca.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.julianawl.movieteca.api.MoviesRepository

class MoviesViewModel(private val repository: MoviesRepository
) : ViewModel(){

    fun getMovies(
        category: String,
        page: Int)
//    : LiveData<Resource<List<Movie>>>
    {



    }

//    private fun onPopularMoviesFetched(movies: List<Movie>) {
//        popularMoviesAdapter.appendMovies(movies)
//        attachPopularMoviesOnScrollListener()
//    }
//
//    private fun attachPopularMoviesOnScrollListener() {
//        popular_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val totalItemCount = popularMoviesLayoutMgr.itemCount
//                val visibleItemCount = popularMoviesLayoutMgr.childCount
//                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()
//
//                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                    popular_movies.removeOnScrollListener(this)
//                    popularMoviesPage++
//                    getPopularMovies()
//                }
//            }
//        })
//    }
//
//    private fun attachTopRatedMoviesOnScrollListener() {
//        top_rated_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
//                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
//                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()
//
//                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                    top_rated_movies.removeOnScrollListener(this)
//                    topRatedMoviesPage++
//                    getTopRatedMovies()
//                }
//            }
//        })
//    }
//
//    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
//        topRatedMoviesAdapter.appendMovies(movies)
//        attachTopRatedMoviesOnScrollListener()
//    }
//
//    private fun attachUpcomingMoviesOnScrollListener() {
//        upcoming_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val totalItemCount = upcomingMoviesLayoutMgr.itemCount
//                val visibleItemCount = upcomingMoviesLayoutMgr.childCount
//                val firstVisibleItem = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()
//
//                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                    upcoming_movies.removeOnScrollListener(this)
//                    upcomingMoviesPage++
//                    getUpcomingMovies()
//                }
//            }
//        })
//    }
//
//    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
//        upcomingMoviesAdapter.appendMovies(movies)
//        attachUpcomingMoviesOnScrollListener()
//    }
}