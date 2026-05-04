package com.mmfsin.tnt.di

import android.content.Context
import androidx.room.Room
import com.mmfsin.tnt.data.bbdd.RoomConfiguration
import com.mmfsin.tnt.data.bbdd.daos.ProductsDAO
import com.mmfsin.tnt.presentation.utils.DDBB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomConfiguration = Room.databaseBuilder(
        context,
        RoomConfiguration::class.java,
        DDBB_NAME
    )
        .fallbackToDestructiveMigration(true)
        .build()

    @Provides
    fun provideProductsDAO(db: RoomConfiguration): ProductsDAO = db.productsDAO()
}