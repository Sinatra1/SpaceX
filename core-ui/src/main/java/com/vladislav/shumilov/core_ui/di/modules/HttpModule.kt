package com.vladislav.shumilov.core_ui.di.modules

import com.google.gson.Gson
import com.vladislav.shumilov.core_data.BuildConfig.API_URL
import com.vladislav.shumilov.core_data.CoreScope
import com.vladislav.shumilov.core_data.api.ApiKeyInterceptor
import com.vladislav.shumilov.core_ui.BuildConfig
import com.vladislav.shumilov.core_ui.BuildConfig.RELEASE
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object HttpModule {

    @Provides
    @JvmStatic
    @CoreScope
    fun provideClient(): OkHttpClient =
        with(OkHttpClient().newBuilder()) {
            addInterceptor(ApiKeyInterceptor())

            if (!BuildConfig.BUILD_TYPE.contains(RELEASE)) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }

            build()
        }

    @Provides
    @JvmStatic
    @CoreScope
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}