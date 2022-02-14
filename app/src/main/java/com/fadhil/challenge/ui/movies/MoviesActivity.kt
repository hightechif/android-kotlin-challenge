package com.fadhil.challenge.ui.movies

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
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.entity.Movie
import com.fadhil.challenge.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private var list: ArrayList<Movie> = arrayListOf()
    private var title: String = "Movies App: Mode List"
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(title)
        binding.rvMovies.setHasFixedSize(true)

        setupObserver()
        showMoviesRecyclerList()
    }

    private fun setupObserver() {
        val movieObserver = Observer<Result<List<Movie>>> {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    setVisibility(RequestStatus.SUCCESS)
                    if (!it.data.isNullOrEmpty()) {
                        list.addAll(it.data)
                    }
                }
                Result.Status.ERROR -> {
                    setVisibility(RequestStatus.ERROR)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.tvErrorMessage.text = it.message
                }
                Result.Status.LOADING -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getMovies().observe(this, movieObserver)
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