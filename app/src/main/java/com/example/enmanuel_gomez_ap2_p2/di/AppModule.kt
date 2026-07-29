package com.example.enmanuel_gomez_ap2_p2.di

import com.example.enmanuel_gomez_ap2_p2.data.remote.api.EntidadApi
import com.example.enmanuel_gomez_ap2_p2.data.repository.EntidadRepositoryImpl
import com.example.enmanuel_gomez_ap2_p2.domain.repository.EntidadRepository
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEntidadApi(retrofit: Retrofit): EntidadApi {
        return retrofit.create(EntidadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEntidadRepository(api: EntidadApi): EntidadRepository {
        return EntidadRepositoryImpl(api)
    }
}
