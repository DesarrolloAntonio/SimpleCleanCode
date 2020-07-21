package com.desarrollodroide.simplecleancode.domain

import android.util.Log
import com.desarrollodroide.simplecleancode.api.ApiService
import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource

class DummyObjectRepositoryImpl(private val apiService: ApiService) : DummyObjectRepository {

    override suspend fun getDummyObjects(): Resource<List<DummyObject>?> {
        return  try {
            val data = apiService.getDataFromServer().body()
            Resource.Success(data)
        } catch (error: Throwable) {
            Resource.Error(error.message?:"", null)
        }
    }
}