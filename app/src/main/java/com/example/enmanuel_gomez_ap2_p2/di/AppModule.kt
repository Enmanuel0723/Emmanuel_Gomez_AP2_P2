package com.example.enmanuel_gomez_ap2_p2.di

import com.example.enmanuel_gomez_ap2_p2.data.remote.api.GastoApi
import com.example.enmanuel_gomez_ap2_p2.data.repository.GastoRepositoryImpl
import com.example.enmanuel_gomez_ap2_p2.domain.repository.GastoRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api-2026-h7eddqgydxc0fmau.eastus2-01.azurewebsites.net/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideGastoApi(retrofit: Retrofit): GastoApi = retrofit.create(GastoApi::class.java)

    @Provides
    @Singleton
    fun provideGastoRepository(api: GastoApi): GastoRepository = GastoRepositoryImpl(api)
}
