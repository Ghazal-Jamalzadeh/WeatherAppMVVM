package com.jmzd.ghazal.weatherappmvvm.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.jmzd.ghazal.weatherappmvvm.utils.CITIES_TABLE

@Dao
interface CitiesDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveCity(entity: CitiesEntity)

    @Delete
    suspend fun deleteCity(entity: CitiesEntity)

    @Query("SELECT * FROM $CITIES_TABLE")
    fun loadCities(): Flow<List<CitiesEntity>>
}