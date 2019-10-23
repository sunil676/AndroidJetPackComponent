package com.sunil.androidjetpackcomponent.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sunil.androidjetpackcomponent.api.MovieDataSource
import com.sunil.androidjetpackcomponent.model.Movie

class MovieDataSourceFactory : DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val dataSource  = MovieDataSource()
        movieLiveDataSource.postValue(dataSource)
        return dataSource
    }

}