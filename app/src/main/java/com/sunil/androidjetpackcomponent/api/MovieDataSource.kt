package com.sunil.androidjetpackcomponent.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sunil.androidjetpackcomponent.model.Movie
import com.sunil.androidjetpackcomponent.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        val service = ApiClient.buildService(MovieService::class.java)
        val call = service.getPopularMovies(FIRST_PAGE)
        call.enqueue(object : Callback<MoviesResponse>{

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.movies

                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val service = ApiClient.buildService(MovieService::class.java)
        val call = service.getPopularMovies(params.key)

        call.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.movies

                    val key = if (apiResponse.totalPages > params.key) params.key + 1 else apiResponse.totalPages

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        val service = ApiClient.buildService(MovieService::class.java)
        val call = service.getPopularMovies(params.key)

        call.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.movies

                    val key = if (params.key > 1) params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("", t.localizedMessage)
            }
        })
    }

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 10
    }

}