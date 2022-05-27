package com.tasks.data.webservice


import com.info.tasks.domain.model.HashDataRequest
import com.info.tasks.domain.model.HashDataResponse
import retrofit2.Response
import retrofit2.http.*

interface DijitalApi {
    @PUT("hash/{slug}")
    suspend fun getTheHashedValue(
        @Path("slug") myStash: String,
        @Body myGuide: HashDataRequest,
    ): Response<HashDataResponse>
}