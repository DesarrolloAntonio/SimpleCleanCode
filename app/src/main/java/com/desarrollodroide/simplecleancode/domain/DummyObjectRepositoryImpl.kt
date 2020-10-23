package com.desarrollodroide.simplecleancode.domain

import com.desarrollodroide.simplecleancode.api.ApiService
import com.desarrollodroide.simplecleancode.database.DummyObjectDao
import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource

class DummyObjectRepositoryImpl(
    private val apiService: ApiService,
    private val dummyObjectDao: DummyObjectDao
) : DummyObjectRepository {

    override suspend fun getDummyObjects(): Resource<List<DummyObject>?> {
        val cachedDummyObjects = dummyObjectDao.getDummyObjects()
        val data = try {
            apiService.getDataFromServer()
        }catch (error: Throwable) {
            null
        }
        data?.body()?.let {
            dummyObjectDao.saveDummyObjects(it)
        }
        return Resource.Success(data?.body() ?: cachedDummyObjects)
    }
}