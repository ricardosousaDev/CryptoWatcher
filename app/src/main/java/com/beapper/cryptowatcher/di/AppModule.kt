package com.beapper.cryptowatcher.di

import com.beapper.cryptowatcher.common.Constants.BASE_URL
import com.beapper.cryptowatcher.data.remote.Api
import com.beapper.cryptowatcher.data.repository.CoinRepositoryImpl
import com.beapper.cryptowatcher.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: Api): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}