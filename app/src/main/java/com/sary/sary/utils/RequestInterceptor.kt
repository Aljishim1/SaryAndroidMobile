package com.sary.sary.utils

import kotlin.Throws
import android.text.TextUtils
import com.sary.sary.BuildConfig
import com.sary.sary.utils.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        if (TextUtils.isEmpty(Constants.JWT)) {
            return chain.proceed(originalRequest)
        }

        val authorizedRequest = originalRequest.newBuilder()
            .header("App-Version", BuildConfig.VERSION_NAME)
            .header("Device-Type", "android")
            .header("Accept-Language", "en")
            .header("Authorization", Constants.JWT)
            .build()

        return chain.proceed(authorizedRequest)
    }
}