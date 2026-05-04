package com.mmfsin.tnt.di

import com.mmfsin.tnt.data.repositories.DataRepository
import com.mmfsin.tnt.domain.interfaces.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindDataRepository(repository: DataRepository): IDataRepository
}