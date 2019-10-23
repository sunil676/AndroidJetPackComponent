package com.sunil.androidjetpackcomponent.model

import com.google.gson.annotations.SerializedName

data class Trailer (

    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String? = null,

    @SerializedName("site")
    var site: String? = null,

    @SerializedName("name")
    var title: String? = null
)
