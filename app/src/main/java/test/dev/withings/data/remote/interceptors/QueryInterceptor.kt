package test.dev.withings.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import test.dev.withings.BuildConfig

class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().addParams()
        return chain.proceed(request)
    }

    private fun Request.addParams(): Request {
        return if (method().equals("GET") && url().host().equals(BuildConfig.HOST)) {
            newBuilder().url(
                url().newBuilder().apply {
                    addQueryParameter("key", BuildConfig.API_KEY)
                }.build()
            ).build()
        } else this
    }
}