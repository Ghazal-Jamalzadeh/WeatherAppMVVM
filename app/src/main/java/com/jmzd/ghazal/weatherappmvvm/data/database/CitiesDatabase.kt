package com.jmzd.ghazal.weatherappmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CitiesEntity::class], version = 1, exportSchema = false)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun dao(): CitiesDao
}