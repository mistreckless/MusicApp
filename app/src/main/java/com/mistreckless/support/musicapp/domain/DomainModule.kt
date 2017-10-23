package com.mistreckless.support.musicapp.domain

import android.content.Context
import android.content.SharedPreferences
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.domain.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by @mistreckless on 23.10.2017. !
 */
@Singleton
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(api: Api, preferences: SharedPreferences): UserRepository = UserRepositoryImpl(api, preferences)

}

@Singleton
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApi() : Api{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        return Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spotify.com")
                .build()
                .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providePreferences(context: Context) : SharedPreferences = context.getSharedPreferences("wow",Context.MODE_PRIVATE)
}