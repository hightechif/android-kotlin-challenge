package com.fadhil.challenge.presentation.heroes

import com.fadhil.challenge.domain.model.Hero

interface HeroCallback {
    fun onItemClicked(data: Hero)
}