package com.fadhil.challenge.ui.heroes

import com.fadhil.challenge.domain.model.Hero

interface HeroCallback {
    fun onItemClicked(data: Hero)
}