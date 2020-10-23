package com.desarrollodroide.simplecleancode.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desarrollodroide.simplecleancode.model.DummyObject

@Database(entities = [DummyObject::class], version = 1)
abstract class DummyObjectDatabase : RoomDatabase() {

  abstract fun dummyObjectDao(): DummyObjectDao

  companion object {

    fun create(context: Context): DummyObjectDatabase {

      return Room.databaseBuilder(
        context,
        DummyObjectDatabase::class.java,
        "table_dummy_objects"
      ).build()
    }
  }
}