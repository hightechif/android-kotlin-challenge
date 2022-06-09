package com.fadhil.challenge.ui.heroes

import com.fadhil.challenge.domain.model.Hero

interface HeroAdapterDelegate {
    fun onItemClicked(data: Hero)
}