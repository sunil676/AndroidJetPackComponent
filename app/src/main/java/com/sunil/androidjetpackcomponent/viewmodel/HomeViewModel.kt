package com.sunil.androidjetpackcomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sunil.androidjetpackcomponent.api.MovieDataSource
import com.sunil.androidjetpackcomponent.model.Movie
import com.sunil.androidjetpackcomponent.paging.MovieDataSourceFactory

class HomeViewModel : ViewModel() {

    var moviePageList : LiveData<PagedList<Movie>>
    private var liveDataSource : LiveData<MovieDataSource>

    init {
        val itemDataSourceFactory = MovieDataSourceFactory()
        liveDataSource = itemDataSourceFactory.movieLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MovieDataSource.PAGE_SIZE)
            .build()

        moviePageList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

}