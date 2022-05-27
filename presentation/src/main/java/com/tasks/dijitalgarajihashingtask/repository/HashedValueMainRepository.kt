package com.info.ionoviewgithuptask.starredprojects.domain.repository

import com.info.tasks.domain.model.HashDataRequest
import com.info.tasks.domain.model.HashDataResponse
import com.tasks.data.webservice.DijitalApi
import com.tasks.domain.util.ErrorType
import com.tasks.domain.util.Resource
import javax.inject.Inject


class HashedValueMainRepository @Inject constructor(var dijitalApi: DijitalApi) :
    HashedValueDefaultRepository {
    override suspend fun getHashedValue(hashRequest: HashDataRequest, mySlug:String): Resource<HashDataResponse> {
        try {
            val repositoryResponse = dijitalApi.getTheHashedValue(
                mySlug,hashRequest
            )
            if (repositoryResponse.isSuccessful) {
                return Resource.Success(repositoryResponse.body())

            } else {
                val errorMsg = repositoryResponse.message()
                return Resource.Error(ErrorType.UNKNOWN, errorMsg)
            }
        } catch (e: Exception) {
            return Resource.Error(ErrorType.UNKNOWN, e.message!!)

        }
    }

}