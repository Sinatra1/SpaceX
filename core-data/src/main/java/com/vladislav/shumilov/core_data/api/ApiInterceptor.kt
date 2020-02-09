package com.vladislav.shumilov.core_data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = with(chain.request()) {
            val httpUrl = url().newBuilder().build()
            newBuilder().url(httpUrl).build()
        }

        return chain.proceed(request)
    }
}