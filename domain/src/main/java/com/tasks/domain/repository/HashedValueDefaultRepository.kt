package com.info.ionoviewgithuptask.starredprojects.domain.repository

import com.info.tasks.domain.model.HashDataRequest
import com.info.tasks.domain.model.HashDataResponse
import com.tasks.domain.util.Resource


interface HashedValueDefaultRepository {
    suspend fun getHashedValue(
        hashRequest:HashDataRequest
        ,mySlug:String
    ): Resource<HashDataResponse>
}