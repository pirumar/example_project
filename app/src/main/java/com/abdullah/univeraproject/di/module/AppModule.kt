package com.abdullah.univeraproject.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentManager
import com.abdullah.univeraproject.MainActivity
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private var baseUrl = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideFragmentManager(mainActivity: MainActivity): FragmentManager =
        mainActivity.supportFragmentManager

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences? {
        return application.getSharedPreferences("login", Context.MODE_PRIVATE)
    }
}