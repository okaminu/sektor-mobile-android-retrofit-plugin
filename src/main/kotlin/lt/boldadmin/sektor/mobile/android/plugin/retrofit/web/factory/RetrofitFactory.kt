package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory

import com.google.gson.GsonBuilder
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.PropertyLoader
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.interceptor.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit.SECONDS

class RetrofitFactory(
    private val propertyLoader: PropertyLoader = PropertyLoader,
    private val builder: Builder = Builder(),
    private val okHttpClient: OkHttpClient = OkHttpClient()
) {

    internal fun <S> create(serviceClass: Class<S>): S {
        return build(defaultOkHttpClientBuilder()).create(serviceClass)
    }

    internal fun <S> create(serviceClass: Class<S>, authToken: String): S {
        return build(defaultOkHttpClientBuilder(), authToken).create(serviceClass)
    }

    private fun build(okHttpClientBuilder: OkHttpClient.Builder) =
        builder.apply {
            baseUrl(propertyLoader.load("config.properties")["BACKEND_URL"].toString())
            client(okHttpClientBuilder.build())
            addConverterFactory(GsonConverterFactory.create((GsonBuilder().setLenient().create())))
        }.build()

    private fun build(okHttpClientBuilder: OkHttpClient.Builder, authToken: String): Retrofit {
        okHttpClientBuilder.interceptors().add(
            AuthenticationInterceptor(
                authToken
            )
        )

        return build(okHttpClientBuilder)
    }

    private fun defaultOkHttpClientBuilder() = okHttpClient.newBuilder().apply {
        val prop = propertyLoader.load(TIMEOUT_PROPERTIES)
        readTimeout(getValue(prop, "READ_TIMEOUT_SECONDS"), SECONDS)
        writeTimeout(getValue(prop, "WRITE_TIMEOUT_SECONDS"), SECONDS)
        connectTimeout(getValue(prop, "CONNECT_TIMEOUT_SECONDS"), SECONDS)
    }

    private fun getValue(prop: Properties, key: String) = prop[key].toString().toLong()

    companion object {
        const val TIMEOUT_PROPERTIES = "timeout.properties"
    }
}
