package com.sunil.androidjetpackcomponent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunil.androidjetpackcomponent.api.ApiClient
import com.sunil.androidjetpackcomponent.api.MovieService
import com.sunil.androidjetpackcomponent.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    val movieLiveDataSource = MutableLiveData<Movie>()

    // get the live data response here
    fun getMovieDetail(movie_id: Long): LiveData<Movie> {
        val service = ApiClient.buildService(MovieService::class.java)
        val call = service.getMovieDetails(movie_id, "videos,credits,reviews")

        call.enqueue(object : Callback<Movie> {

            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {

                if (response.isSuccessful) {

                    val apiResponse = response.body()!!
                    // val responseItems = apiResponse

                    apiResponse?.let {
                        movieLiveDataSource.postValue(apiResponse)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("", t.localizedMessage)
            }
        })
        return movieLiveDataSource
    }


}