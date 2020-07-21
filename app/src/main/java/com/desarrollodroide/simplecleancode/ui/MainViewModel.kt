package com.desarrollodroide.simplecleancode.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desarrollodroide.simplecleancode.domain.DummyObjectRepository
import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(private val dummyObjectRepository: DummyObjectRepository) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        throwable.printStackTrace()
        dummyObjects.postValue(Resource.Error("Something Went Wrong", null))
    }

    var dummyObjects = MutableLiveData<Resource<List<DummyObject>?>>()
    var error = MutableLiveData<String>()
    var progress = MutableLiveData<Boolean>()

    fun getData() {
        dummyObjects.postValue(Resource.Loading(null))
        progress.value = true
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = runCatching { dummyObjectRepository.getDummyObjects() }
            progress.value = false
            result.onSuccess { responseDummyObjects ->
                dummyObjects.postValue(responseDummyObjects)
            }.onFailure { error ->
                dummyObjects.postValue(Resource.Error(error.message, null))
            }
        }
    }
}