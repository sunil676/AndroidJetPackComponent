package com.sunil.androidjetpackcomponent.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie (

    @SerializedName("id")
    var id: Long = 0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double = 0.toDouble(),

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble(),

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    var isFavorite: Boolean = false,

    @SerializedName("genres")
    var genres: List<Genre>? = null,

    @SerializedName("videos")
    var trailersResponse: TrailersResponse? = null,

    @SerializedName("credits")
    var creditsResponse: CreditsResponse? = null,


    @SerializedName("reviews")
    var reviewsResponse: ReviewsResponse? = null) {

    @SerializedName("original_language")
    var originalLanguage: String? = null
        get() = field?.substring(0, 1)?.toUpperCase() + field?.substring(1)

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val movie = o as? Movie
        return id == movie?.id &&
                movie.popularity.compareTo(popularity) == 0 &&
                movie.voteAverage.compareTo(voteAverage) == 0 &&
                title == movie.title &&
                posterPath == movie.posterPath &&
                overview == movie.overview &&
                releaseDate == movie.releaseDate
    }

    override fun hashCode(): Int {
        return Objects.hash(id, title, posterPath, overview, popularity, voteAverage, releaseDate)
    }
}
