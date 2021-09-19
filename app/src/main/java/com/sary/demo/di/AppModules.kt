package com.sary.demo.di

import android.os.Environment
import com.sary.demo.BuildConfig
import com.sary.demo.data.models.remote.APIs
import com.sary.demo.data.models.remote.RemoteRepositoryImp
import com.sary.demo.data.models.remote.RemoteRepository
import com.sary.demo.utils.NullOnEmptyConverterFactory
import com.sary.demo.utils.RequestInterceptor
import com.sary.demo.ui.main.categories.CategoriesViewModel
import com.sary.demo.utils.Constants
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

var viewModelModule = module {
    viewModel { CategoriesViewModel(repository = get()) }
}

var repositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImp(api = get())  }
}

val serviceAPIModule = module {

    fun getRetroBuilder(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.ENDPOINT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientInstance())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .build()
    }

    single { getRetroBuilder() }

    fun getServiceAPIInstance(retrofit: Retrofit): APIs {
        return retrofit.create(APIs::class.java)
    }

    single { getServiceAPIInstance(retrofit = get()) }

}

private fun okHttpClientInstance(): OkHttpClient {
    val readTimeout: Long = 30
    val writeTimeout: Long = 30
     val connectTimeout: Long = 30
     val maxIdleConnection: Int = 4
     val keepAliveDuration: Long = 3000
     val dataDirCacheSize: Long = 1024 * 5;
     val downloadDirCacheSize: Long = 1024 * 2 * 1024;
     val cpuCount: Int = Runtime.getRuntime().availableProcessors()
     val corePoolSize: Int = cpuCount + 1
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