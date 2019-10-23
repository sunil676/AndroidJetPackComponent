package com.sunil.androidjetpackcomponent.model

import com.google.gson.annotations.SerializedName

data class Review (
    @SerializedName("id")
    var id: String,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("content")
    var content: String? = null,

    @SerializedName("url")
    var url: String? = null
)
