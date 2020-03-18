package org.sigmapool.api.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class HeaderInterceptor(private val headerMappers: ArrayList<HeaderMapper>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (headerMappers.isEmpty()) {
            return chain.proceed(request)
        }

        for (headerMapper in headerMappers) {
            if (headerMapper.isValueValid()) {
                request = mapRequestHeaders(headerMapper, request)
            }
        }

        val result = chain.proceed(request)

        for (headerMapper in headerMappers) {
            headerMapper.responseProceed(result)
        }

        return result
    }

    /*** Обновление Header у запросов к API */
    private fun mapRequestHeaders(headerMapper: HeaderMapper, request: Request): Request {
        return if (!headerMapper.isValueValid()) {
            request
        } else request.newBuilder()
            .removeHeader(headerMapper.getHeaderName())
            .addHeader(headerMapper.getHeaderName(), headerMapper.getValue())
            .build()
    }
}