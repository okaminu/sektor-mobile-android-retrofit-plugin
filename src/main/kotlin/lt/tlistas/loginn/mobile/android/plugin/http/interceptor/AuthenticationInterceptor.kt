package lt.tlistas.loginn.mobile.android.plugin.http.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response


class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val builder = chain.request().newBuilder().header("auth-token", authToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}