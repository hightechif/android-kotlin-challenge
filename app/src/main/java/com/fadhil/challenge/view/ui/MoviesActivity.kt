package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.R
import com.fadhil.challenge.constant.RequestStatus
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.data.source.remote.RemoteDataSource
import com.fadhil.challenge.data.source.remote.network.MoviesApi
import com.fadhil.challenge.data.source.remote.network.MoviesService
import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.databinding.ActivityMoviesBinding
import com.fadhil.challenge.model.Movie
import com.fadhil.challenge.view.adapter.MovieCardViewAdapter
import com.fadhil.challenge.view.adapter.MovieGridAdapter
import com.fadhil.challenge.view.adapter.MovieListAdapter
import com.fadhil.challenge.view.callback.MovieCallback
import com.fadhil.challenge.viewmodels.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private var list: ArrayList<Movie> = arrayListOf()
    private var title: String = "Movies App: Mode List"
    inner class MoviesServiceImpl: MoviesService {
        override fun getMovieList(page: Int, apiKey: String): Call<BaseResponse<Movie>> {
            // override method
        }

    }
    private val moviesServiceImpl: MoviesServiceImpl = MoviesServiceImpl()
    private val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource(moviesServiceImpl)
    }
    private val movieRepository: MovieRepository by lazy {
        MovieRepository(remoteDataSource)
    }
    private val model: MovieViewModel by lazy {
        MovieViewModel(movieRepository)
    }
    private var page: Int = 1

    companion object {
        private const val API_KEY: String = "31bcf72a6584461df2daa00c58f75514"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(title)
        binding.rvMovies.setHasFixedSize(true)

        val movieObserver = Observer<List<Movie>> {
            list.addAll(it)
        }

        model.getMovies().observe(this, movieObserver)
//        lifecycleScope.launch {
//            fetchMovieList()
//        }
        showMoviesRecyclerList()
    }

    private fun fetchMovieList() {
        MoviesApi.instance.getMovieList(page, API_KEY)
            .enqueue(object : Callback<BaseResponse<Movie>> {
                override fun onResponse(
                    call: Call<BaseResponse<Movie>>,
                    response: Response<BaseResponse<Movie>>
                ) {
                    setVisibility(RequestStatus.SUCCESS)
                    val responseBody = response.body()
                    Timber.i("HTTP Response: $response")
                    responseBody?.results?.let { list.addAll(it) }
                }

                override fun onFailure(call: Call<BaseResponse<Movie>>, t: Throwable) {
                    setVisibility(RequestStatus.ERROR)
                    binding.tvErrorMessage.text = t.message
                }
            })
    }

    private fun showMoviesRecyclerList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val movieListAdapter = MovieListAdapter(list)
        binding.rvMovies.adapter = movieListAdapter
        movieListAdapter.setOnClickedCallback(object : MovieCallback {
            override fun onItemClicked(data: Movie) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(movie: Movie) {
        Toast.makeText(this, "You click " + movie.title, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Movies App: Mode List"
                showMoviesRecyclerList()
            }
            R.id.action_grid -> {
                title = "Movies App: Mode Grid"
                showMoviesRecyclerGrid()
            }
            R.id.action_cardView -> {
                title = "Movies App: Mode CardView"
                showMoviesRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }

    private fun showMoviesRecyclerGrid() {
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        val movieGridAdapter = MovieGridAdapter(list)
        binding.rvMovies.adapter = movieGridAdapter
        movieGridAdapter.setOnClickedCallback(object : MovieCallback {
            override fun onItemClicked(data: Movie) {
                showSelectedHero(data)
            }
        })
    }

    private fun showMoviesRecyclerCardView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val movieCardViewAdapter = MovieCardViewAdapter(list)
        binding.rvMovies.adapter = movieCardViewAdapter
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setVisibility(status: RequestStatus) {
        binding.rvMovies.visibility = View.GONE
        binding.tvErrorMessage.visibility = View.GONE
        when (status) {
            RequestStatus.SUCCESS -> {
                binding.rvMovies.visibility = View.VISIBLE
            }
            RequestStatus.ERROR -> {
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
        }
    }
}