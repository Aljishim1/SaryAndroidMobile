package com.sary.sary.utils

import retrofit2.Retrofit
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * to convert empty values to null
 */

class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { if (it.contentLength() == 0L) null else delegate.convert(it) }
    }
}