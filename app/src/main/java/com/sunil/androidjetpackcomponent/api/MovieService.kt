package com.sunil.androidjetpackcomponent.api

import androidx.lifecycle.LiveData
import com.sunil.androidjetpackcomponent.BuildConfig
import com.sunil.androidjetpackcomponent.model.Movie
import com.sunil.androidjetpackcomponent.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getPopularMovies(@Query("page") page: Int): Call<MoviesResponse>


    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}")
    fun getMovieDetails(@Path("id") id: Long, @Query("append_to_response") response : String):  Call<Movie>
}