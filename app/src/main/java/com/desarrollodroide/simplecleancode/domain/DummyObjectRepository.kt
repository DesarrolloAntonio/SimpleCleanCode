package com.desarrollodroide.simplecleancode.domain

import com.desarrollodroide.simplecleancode.model.DummyObject
import com.desarrollodroide.simplecleancode.model.Resource

interface DummyObjectRepository {
  suspend fun getDummyObjects(): Resource<List<DummyObject>?>
}