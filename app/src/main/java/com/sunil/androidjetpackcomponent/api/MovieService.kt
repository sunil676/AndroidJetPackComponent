package com.sunil.androidjetpackcomponent.api

import com.sunil.androidjetpackcomponent.BuildConfig
import com.sunil.androidjetpackcomponent.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getPopularMovies(@Query("page") page: Int): Call<MoviesResponse>
}