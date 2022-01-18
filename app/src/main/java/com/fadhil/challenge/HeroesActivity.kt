package com.fadhil.challenge

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.adapter.HeroListAdapter
import com.fadhil.challenge.data.HeroesData
import com.fadhil.challenge.databinding.ActivityHeroesBinding
import com.fadhil.challenge.model.Hero

class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding
    private var list: ArrayList<Hero> = arrayListOf()
    private var title: String = "Heroes App: Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setActionBarTitle(title)

        binding.rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)

        val heroListAdapter = HeroListAdapter(list)
        binding.rvHeroes.adapter = heroListAdapter
        heroListAdapter.setOnClickedCallback(object : HeroListAdapter.OnItemClickedCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Anda memilih " + hero.name, Toast.LENGTH_SHORT).show()
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
        }
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}