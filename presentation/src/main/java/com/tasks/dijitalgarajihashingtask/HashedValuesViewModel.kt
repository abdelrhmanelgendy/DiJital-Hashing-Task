package com.tasks.dijitalgarajihashingtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.ionoviewgithuptask.starredprojects.domain.repository.HashedValueMainRepository
import com.info.tasks.domain.model.HashDataRequest
import com.info.tasks.domain.model.HashDataResponse
import com.tasks.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HashedValuesViewModel @Inject constructor(
    private val hashedValueMainRepository: HashedValueMainRepository
) : ViewModel() {


    private val _hashValueMutableStateFlow: MutableStateFlow<Resource<HashDataResponse>> =
        MutableStateFlow(Resource.Idle())
    val hashValueStateFlow: StateFlow<Resource<HashDataResponse>> =
        _hashValueMutableStateFlow


    fun getHashedEmail(hashDataRequest: HashDataRequest,slug:String) {
        viewModelScope.launch {
            _hashValueMutableStateFlow.emit(Resource.Loading())
            val hashedResult = hashedValueMainRepository.getHashedValue(hashDataRequest,slug)
            _hashValueMutableStateFlow.emit(Resource.Success(hashedResult.data))
        }
    }

}