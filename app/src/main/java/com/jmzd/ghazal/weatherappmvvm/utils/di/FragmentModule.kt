package com.jmzd.ghazal.weatherappmvvm.utils.di

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {
    @Provides
    fun cityEntity() = CitiesEntity()
}