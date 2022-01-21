package com.fadhil.challenge

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.adapter.MovieCallbackInterface
import com.fadhil.challenge.adapter.MovieCardViewAdapter
import com.fadhil.challenge.adapter.MovieGridAdapter
import com.fadhil.challenge.adapter.MovieListAdapter
import com.fadhil.challenge.constant.enum.RequestStatus
import com.fadhil.challenge.databinding.ActivityMoviesBinding
import com.fadhil.challenge.model.BaseResponse
import com.fadhil.challenge.model.Movie
import com.fadhil.challenge.retrofit.MovieRetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private var list: ArrayList<Movie> = arrayListOf()
    private var title: String = "Movies App: Mode List"
    private var page: Int = 1

    companion object {
        private const val API_KEY: String = "31bcf72a6584461df2daa00c58f75514"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setActionBarTitle(title)

        binding.rvMovies.setHasFixedSize(true)
        GlobalScope.launch {
            fetchMovieList()
        }
        showMoviesRecyclerList()
    }

    private suspend fun fetchMovieList() {
        MovieRetrofitClient.instance.getMovieList(page, API_KEY)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    setVisibility(RequestStatus.SUCCESS)
                    val responseBody = response.body()
                    Timber.i("HTTP Response: $response")
                    responseBody?.results?.let { list.addAll(it) }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    setVisibility(RequestStatus.ERROR)
                    binding.tvErrorMessage.text = t.message
                }

            })
    }

    private fun showMoviesRecyclerList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val movieListAdapter = MovieListAdapter(list)
        binding.rvMovies.adapter = movieListAdapter
        movieListAdapter.setOnClickedCallback(object : MovieCallbackInterface {
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
        movieGridAdapter.setOnClickedCallback(object : MovieCallbackInterface {
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