package com.myapplications.wholetthedogsout.di

import android.content.Context
import com.myapplications.wholetthedogsout.data.UrlsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUrlsRepository(@ApplicationContext context : Context) : UrlsRepository{
        return UrlsRepository(context)
    }

}