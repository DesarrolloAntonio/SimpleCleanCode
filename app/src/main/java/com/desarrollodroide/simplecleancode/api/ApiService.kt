package com.desarrollodroide.simplecleancode.api

import com.desarrollodroide.simplecleancode.model.DummyObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
  @GET("posts")
  suspend fun getDataFromServer(): Response<List<DummyObject>>
}
