package com.fadhil.challenge.view.callback

import com.fadhil.challenge.data.entities.Hero

interface HeroCallback {
    fun onItemClicked(data: Hero)
}