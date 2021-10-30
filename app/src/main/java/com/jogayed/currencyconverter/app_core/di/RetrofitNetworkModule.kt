package com.jogayed.currencyconverter.app_core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jogayed.currencyconverter.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNetworkModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}