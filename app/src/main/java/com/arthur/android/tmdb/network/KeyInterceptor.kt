package com.arthur.android.tmdb.network

import com.arthur.android.tmdb.BuildConfig
import com.arthur.android.tmdb.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

object KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()
        val newUrl = originalUrl.newBuilder()
            .setQueryParameter(API_KEY, BuildConfig.ApiKey)
            .build()

        val request = original.newBuilder().url(newUrl).build()
        return chain.proceed(request)
    }
}
