package com.fadhil.challenge.ui.heroes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.R
import com.fadhil.challenge.constant.ViewMode
import com.fadhil.challenge.constant.HeroesData
import com.fadhil.challenge.databinding.ActivityHeroesBinding
import com.fadhil.challenge.domain.model.Hero

class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding
    private var list: ArrayList<Hero> = arrayListOf()
    private var title: String = "Heroes App: Mode List"
    private lateinit var listView: ListView
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(title)
        binding.rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        setVisibility(ViewMode.RECYCLE_VIEW)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)

        val heroListAdapter = HeroListAdapter(list)
        binding.rvHeroes.adapter = heroListAdapter
        heroListAdapter.setOnClickedCallback(object : HeroCallback {
            override fun onItemClicked(data: Hero) {
                showHeroesDetailPage(data)
            }
        })
    }

    private fun setVisibility(mode: ViewMode) {
        binding.rvHeroes.visibility = View.GONE
        binding.lvHeroes.visibility = View.GONE
        binding.gvHeroes.visibility = View.GONE
        when (mode) {
            ViewMode.RECYCLE_VIEW -> {
                binding.rvHeroes.visibility = View.VISIBLE
            }
            ViewMode.LIST_VIEW -> {
                binding.lvHeroes.visibility = View.VISIBLE
            }
            ViewMode.GRID_VIEW -> {
                binding.gvHeroes.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Heroes App: Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Heroes App: Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardView -> {
                title = "Heroes App: Mode CardView"
                showRecyclerCardView()
            }
            R.id.action_listView -> {
                title = "Heroes App: Mode ListView"
                showListView()
            }
            R.id.action_gridView -> {
                title = "Heroes App: Mode GridView"
                showGridView()
            }
        }
        setActionBarTitle(title)
    }

    private fun showRecyclerGrid() {
        setVisibility(ViewMode.RECYCLE_VIEW)
        binding.rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val heroGridAdapter = HeroGridAdapter(list)
        binding.rvHeroes.adapter = heroGridAdapter

        heroGridAdapter.setOnClickedCallback(object : HeroCallback {
            override fun onItemClicked(data: Hero) {
                showHeroesDetailPage(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        setVisibility(ViewMode.RECYCLE_VIEW)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val heroCardViewAdapter = HeroCardViewAdapter(list)
        binding.rvHeroes.adapter = heroCardViewAdapter
    }

    private fun showListView() {
        setVisibility(ViewMode.LIST_VIEW)
        listView = findViewById(R.id.lv_heroes)
        val heroListViewAdapter = HeroListViewAdapter(this, list)
        listView.adapter = heroListViewAdapter

        heroListViewAdapter.setOnClickedCallback(object : HeroCallback {
            override fun onItemClicked(data: Hero) {
                showHeroesDetailPage(data)
            }
        })
    }

    private fun showGridView() {
        setVisibility(ViewMode.GRID_VIEW)
        gridView = findViewById(R.id.gv_heroes)
        gridView.numColumns = 2
        val heroGridViewAdapter = HeroGridViewAdapter(this, list)
        gridView.adapter = heroGridViewAdapter

        heroGridViewAdapter.setOnClickedCallback(object : HeroCallback {
            override fun onItemClicked(data: Hero) {
                showHeroesDetailPage(data)
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showHeroesDetailPage(hero: Hero) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HeroDetailActivity::class.java)
        intent.putExtra("HERO_NAME", hero.name)
        intent.putExtra("HERO_DETAIL", hero.detail)
        intent.putExtra("HERO_PHOTO", hero.photo)
        startActivity(intent)
    }
}