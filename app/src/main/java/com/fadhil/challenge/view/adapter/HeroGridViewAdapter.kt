package com.fadhil.challenge.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.fadhil.challenge.R
import com.fadhil.challenge.data.entities.Hero
import com.fadhil.challenge.view.callback.HeroCallback
import timber.log.Timber

class HeroGridViewAdapter(private val context: Context, private val heroList: ArrayList<Hero>) : BaseAdapter() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var onItemClickedCallback: HeroCallback

    fun setOnClickedCallback(callback: HeroCallback) {
        this.onItemClickedCallback = callback
    }

    override fun getCount(): Int = heroList.size

    override fun getItem(position: Int): Any {
        return heroList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val hero = heroList[position]
        Timber.i("Data Hero: $position")
        var gridView = convertView
        if (gridView == null) {
            gridView = inflater.inflate(R.layout.item_grid_hero, parent, false)
        }
        val imgPhoto: ImageView = gridView!!.findViewById(R.id.img_item_photo)
        imgPhoto.setImageResource(hero.photo)
        gridView.setOnClickListener{
            onItemClickedCallback.onItemClicked(hero)
        }
        return gridView
    }
}