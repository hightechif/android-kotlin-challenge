package com.fadhil.challenge.adapter

import com.fadhil.challenge.model.Hero

interface HeroCallbackInterface {
    fun onItemClicked(data: Hero)
}