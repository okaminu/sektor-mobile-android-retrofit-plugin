package lt.tlistas.loginn.mobile.android.plugin.web.factory

import com.google.gson.GsonBuilder
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import lt.tlistas.loginn.mobile.android.plugin.web.interceptor.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit.SECONDS

class RetrofitFactory(
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val builder: Builder = Builder(),
    private val okHttpClient: OkHttpClient = OkHttpClient()
) {

    private val defaultHttpClientBuilder = defaultOkHttpClientBuilder()

    fun <S> create(serviceClass: Class<S>): S {
        return build(defaultHttpClientBuilder).create(serviceClass)
    }

    fun <S> create(serviceClass: Class<S>, authToken: String): S {
        defaultHttpClientBuilder.interceptors().add(AuthenticationInterceptor(authToken))

        return build(defaultHttpClientBuilder).create(serviceClass)
    }

    private fun build(okHttpClientBuilder: OkHttpClient.Builder) = builder.apply {
        baseUrl(propertyLoader.load("config.properties")["BACKEND_URL"].toString())
        client(okHttpClientBuilder.build())
        addConverterFactory(GsonConverterFactory.create((GsonBuilder().setLenient().create())))
    }.build()

    private fun defaultOkHttpClientBuilder() = okHttpClient.newBuilder().apply {
        val prop = propertyLoader.load(TIMEOUT_PROPERTIES)
        readTimeout(getValue(prop, "READ_TIMEOUT"), SECONDS)
        writeTimeout(getValue(prop, "WRITE_TIMEOUT"), SECONDS)
        connectTimeout(getValue(prop, "CONNECT_TIMEOUT"), SECONDS)
    }

    private fun getValue(prop: Properties, key: String) = prop[key].toString().toLong()

    companion object {
        const val TIMEOUT_PROPERTIES = "timeout.properties"
    }
}