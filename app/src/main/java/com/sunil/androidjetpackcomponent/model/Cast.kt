package com.sunil.androidjetpackcomponent.model

import com.google.gson.annotations.SerializedName

data class Cast (

    @SerializedName("credit_id")
    var id: String,

    @SerializedName("character")
    var characterName: String? = null,

    @SerializedName("gender")
    var gender: Int = 0,

    @SerializedName("name")
    var actorName: String? = null,

    @SerializedName("order")
    var order: Int = 0,

    @SerializedName("profile_path")
    var profileImagePath: String? = null
)
