package br.com.julianawl.movieteca.api
//
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import br.com.julianawl.movieteca.Category
//import br.com.julianawl.movieteca.data.Movie
//
//
//    fun onMoviesFetched(listMovies: List<Movie>) {
//        popularMoviesAdapter.appendMovies(listMovies)
//        attachMoviesOnScrollListener(
//            popularMoviesLayoutManager,
//            popularMovies,
//            popularMoviesPage,
//            Category.POPULAR
//        )
//
//
//    private fun onTopRatedMoviesFetched(listMovies: List<Movie>) {
//        topRatedMoviesAdapter.appendMovies(listMovies)
//        attachMoviesOnScrollListener(
//            topRatedMoviesLayoutManager,
//            topRatedMovies,
//            topRatedMoviesPage,
//            Category.TOP_RATED
//        )
//    }
//
//    private fun onUpcomingMoviesFetched(listMovies: List<Movie>) {
//        upcomingMoviesAdapter.appendMovies(listMovies)
//        attachMoviesOnScrollListener(
//            upcomingMoviesLayoutManager,
//            upcomingMovies,
//            upcomingMoviesPage,
//            Category.UPCOMING
//        )
//    }
//
//    private fun attachMoviesOnScrollListener(
//        layoutManager: LinearLayoutManager,
//        movies: RecyclerView,
//        pages: Int,
//        category: Category
//    ) {
//        movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val totalItemCount = layoutManager.itemCount
//                val visibleItemCount = layoutManager.childCount
//                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
//
//                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
//                    movies.removeOnScrollListener(this)
//                    pages + 1
//                    when (category) {
//                        Category.POPULAR -> getPopularMovies()
//                        Category.TOP_RATED -> getTopRatedMovies()
//                        Category.UPCOMING -> getUpcomingMovies()
//                    }
//                }
//            }
//        })
//    }
//}