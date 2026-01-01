package com.perpushub.bandung.ui.main.home

sealed class HomeEvent {
    class OnSearchQueryChange(val query: String) : HomeEvent()
    object OnBooksSearch : HomeEvent()
}