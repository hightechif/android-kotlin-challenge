package com.fadhil.challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.fadhil.challenge.R
import com.fadhil.challenge.model.Hero
import timber.log.Timber

class HeroGridViewAdapter(private val context: Context, private val heroList: ArrayList<Hero>) : BaseAdapter() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return heroList.size
    }

    override fun getItem(position: Int): Any {
        return heroList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val hero = heroList[position]
        Timber.i("Data Hero: $position")
        val gridView = inflater.inflate(R.layout.item_grid_hero, parent, false)
        val imgPhoto: ImageView = gridView.findViewById(R.id.img_item_photo)
        imgPhoto.setImageResource(hero.photo)
        return gridView
    }
}