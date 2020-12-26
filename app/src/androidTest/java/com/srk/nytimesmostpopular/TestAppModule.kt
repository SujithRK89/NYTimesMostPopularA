package com.srk.nytimesmostpopular

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.srk.nytimesmostpopular.data.remote.APIService
import com.srk.nytimesmostpopular.data.remote.RemoteDataSource
import com.srk.nytimesmostpopular.data.remote.repository.Repository
import com.srk.nytimesmostpopular.di.ApiKey
import com.srk.nytimesmostpopular.utils.RestConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by sujithrk on 26,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideBaseUrl(): String = if (RestConfig.IS_PRODUCTION) RestConfig.BASE_URL_PRO else RestConfig.BASE_URL_DEV

    @Provides
    fun provideInterceptor() = Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .addHeader(RestConfig.CONTENT_TYPE_KEY, RestConfig.CONTENT_TYPE_VALUE)
            .build()

        chain.proceed(newRequest)
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor) = if(BuildConfig.DEBUG){
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(RestConfig.READ_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(RestConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }else{
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(RestConfig.READ_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(RestConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource) = Repository(remoteDataSource)

    @ApiKey
    @Singleton
    @Provides
    fun provideApiKey(): String = RestConfig.API_KEY
}