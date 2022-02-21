package com.fadhil.challenge.constant

sealed class ViewMode {
    object RECYCLERVIEW : ViewMode()
    object LISTVIEW : ViewMode()
    object GRIDVIEW : ViewMode()
}