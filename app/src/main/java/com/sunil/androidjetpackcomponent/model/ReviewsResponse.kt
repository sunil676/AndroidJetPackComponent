package com.sunil.androidjetpackcomponent.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse (

    @SerializedName("results")
    var reviews: List<Review>? = null
)
