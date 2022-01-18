package com.fadhil.challenge.adapter

import com.fadhil.challenge.model.Hero

interface OnItemClickedCallback {
    fun onItemClicked(data: Hero)
}