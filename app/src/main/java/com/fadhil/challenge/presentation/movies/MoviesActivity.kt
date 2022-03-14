package com.fadhil.challenge.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.R
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.databinding.ActivityMoviesBinding
import com.fadhil.challenge.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private var movieList: MutableList<Movie> = mutableListOf()
    private var title: String = "Movies App: Mode List"
    private val viewModel: MoviesViewModel by viewModels()
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(title)
        binding.rvMovies.setHasFixedSize(true)

        loadData(page)
        showMoviesRecyclerList()
        onScrollSetup()
    }

    private fun loadData(page: Int) {
        getMovieList(page)
    }

    private fun getMovieList(page: Int) {
        viewModel.getMovies(page).observe(this) {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    setVisibility(Result.Status.SUCCESS)
                    if (!it.data.isNullOrEmpty()) {
                        movieList.addAll(it.data)
                    }
                }
                Result.Status.ERROR -> {
                    setVisibility(Result.Status.ERROR)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.tvErrorMessage.text = it.message
                }
                Result.Status.LOADING -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showMoviesRecyclerList() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = linearLayoutManager
        val movieListAdapter = MovieListAdapter(movieList)
        binding.rvMovies.adapter = movieListAdapter
        movieListAdapter.setOnClickedCallback(object : MovieAdapterDelegate {
            override fun onItemClicked(data: Movie) {
                showMovieDetailPage(data)
            }

            override fun onShare(data: Movie) {
            }
        })
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
        val movieGridAdapter = MovieGridAdapter(movieList)
        binding.rvMovies.adapter = movieGridAdapter
        movieGridAdapter.setOnClickedCallback(object : MovieAdapterDelegate {
            override fun onItemClicked(data: Movie) {
                showMovieDetailPage(data)
            }

            override fun onShare(data: Movie) {
            }
        })
    }

    private fun showMoviesRecyclerCardView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val movieCardViewAdapter = MovieCardViewAdapter(movieList)
        binding.rvMovies.adapter = movieCardViewAdapter
        movieCardViewAdapter.setOnClickedCallback(object : MovieAdapterDelegate {
            override fun onItemClicked(data: Movie) {
                showMovieDetailPage(data)
            }

            override fun onShare(data: Movie) {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "${data.title}: ${data.overview}")
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, data.title)
                startActivity(Intent.createChooser(sharingIntent, "Share movie"))
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setVisibility(status: Result.Status) {
        binding.rvMovies.visibility = View.GONE
        binding.tvErrorMessage.visibility = View.GONE
        when (status) {
            Result.Status.SUCCESS -> {
                binding.rvMovies.visibility = View.VISIBLE
            }
            Result.Status.ERROR -> {
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
            else -> {}
        }
    }

    private fun showMovieDetailPage(movie: Movie) {
        Toast.makeText(this, "Kamu memilih " + movie.title, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_TITLE, movie.title)
        intent.putExtra(MovieDetailActivity.MOVIE_RATING, movie.rating)
        intent.putExtra(MovieDetailActivity.MOVIE_RELEASE, movie.release)
        intent.putExtra(MovieDetailActivity.MOVIE_OVERVIEW, movie.overview)
        intent.putExtra(MovieDetailActivity.MOVIE_POSTER, movie.poster)
        startActivity(intent)
    }

    private fun onScrollSetup() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = linearLayoutManager
        // Retain an instance so that you can call `resetState()` for fresh searches
        // Retain an instance so that you can call `resetState()` for fresh searches
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    onLast()
                }
            }
        })
    }

    private fun onLast() {
        Toast.makeText(this, "Reach last movie item.", Toast.LENGTH_SHORT).show()
    }

}