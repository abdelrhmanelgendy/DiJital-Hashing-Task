package com.info.tasks.domain.model

import com.google.gson.annotations.SerializedName

data class HashDataResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("hash")

    val hash: String,
    @SerializedName("name")

    val name: String
)