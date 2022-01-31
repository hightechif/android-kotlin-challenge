package com.fadhil.challenge.view.callback

import com.fadhil.challenge.model.Hero

interface HeroCallback {
    fun onItemClicked(data: Hero)
}