package com.example.binlist.di

import android.content.Context
import com.example.binlist.data.local.AppDatabase
import com.example.binlist.data.remote.BinApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideBinApiService(retrofit: Retrofit): BinApiService =
        retrofit.create(BinApiService::class.java)

    /*@Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)
   */

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBinDao(db: AppDatabase) = db.binDao()

    /*@Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao) =
        CharacterRepository(remoteDataSource, localDataSource)*/
}