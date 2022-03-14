package com.fadhil.challenge.presentation.heroes

import com.fadhil.challenge.domain.model.Hero

interface HeroAdapterDelegate {
    fun onItemClicked(data: Hero)
}