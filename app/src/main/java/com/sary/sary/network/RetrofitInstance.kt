package com.sary.sary.network

import android.os.Environment
import com.sary.sary.BuildConfig
import com.sary.sary.utils.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RetrofitInstance() {
    private val readTimeout: Long = 30
    private val writeTimeout: Long = 30
    private val connectTimeout: Long = 30
    private val maxIdleConnection: Int = 4
    private val keepAliveDuration: Long = 3000
    private val dataDirCacheSize: Long = 1024 * 5;
    private val downloadDirCacheSize: Long = 1024 * 2 * 1024;
    private val cpuCount: Int = Runtime.getRuntime().availableProcessors()
    private val corePoolSize: Int = cpuCount + 1

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(Constants.ENDPOINT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientInstance())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .build()

    private fun okHttpClientInstance(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val interceptor = Interceptor { it.proceed(it.request()) }

        // Display logs just in debugging mode
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        builder.addNetworkInterceptor(interceptor)
            .connectionPool(ConnectionPool(corePoolSize, keepAliveDuration, TimeUnit.MILLISECONDS))
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(RequestInterceptor())
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(Cache(Environment.getDownloadCacheDirectory(), downloadDirCacheSize))
            .cache(Cache(Environment.getDataDirectory(), dataDirCacheSize))
            .dispatcher(Dispatcher(Executors.newFixedThreadPool(maxIdleConnection)))
            .build()

        return builder.build()
    }
}