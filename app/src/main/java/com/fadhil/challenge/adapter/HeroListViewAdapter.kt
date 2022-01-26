package com.fadhil.challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fadhil.challenge.R
import com.fadhil.challenge.model.Hero
import timber.log.Timber

class HeroListViewAdapter(private val context: Context, private val heroList: ArrayList<Hero>) : BaseAdapter() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var onItemClickedCallback: HeroCallbackInterface

    fun setOnClickedCallback(callback: HeroCallbackInterface) {
        this.onItemClickedCallback = callback
    }

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
        var rowView = convertView
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_row_hero, parent, false)
        }
        val tvName: TextView = rowView!!.findViewById(R.id.tv_item_name)
        val tvDetail: TextView = rowView.findViewById(R.id.tv_item_gpa)
        val imgPhoto: ImageView = rowView.findViewById(R.id.img_item_photo)
        tvName.text = hero.name
        tvDetail.text = hero.detail
        imgPhoto.setImageResource(hero.photo)
        rowView.setOnClickListener {
            onItemClickedCallback.onItemClicked(hero)
        }
        return rowView
    }
}