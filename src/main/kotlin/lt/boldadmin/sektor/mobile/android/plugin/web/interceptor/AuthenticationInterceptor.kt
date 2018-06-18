package lt.boldadmin.sektor.mobile.android.plugin.web.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response


class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("auth-token", authToken)
            .build()

        return chain.proceed(request)
    }
}