package com.info.tasks.domain.model

import com.google.gson.annotations.SerializedName

data class HashDataRequest(
    @SerializedName("GUID")
    val guide: String
)