package lt.tlistas.loginn.mobile.android.plugin.http

import com.google.gson.GsonBuilder
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import lt.tlistas.loginn.mobile.android.plugin.http.interceptor.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceFactory(private val propertyLoader: PropertyLoader = PropertyLoader(),
                             private val builder: Builder = Builder(),
                             private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClientBuilder().defaultBuilder()) {

    fun <S> buildService(serviceClass: Class<S>): S {
        return build(okHttpClientBuilder).create(serviceClass)
    }

    fun <S> buildService(serviceClass: Class<S>, authToken: String): S {
        okHttpClientBuilder.interceptors().add(AuthenticationInterceptor(authToken))

        return build(okHttpClientBuilder).create(serviceClass)
    }

    private fun build(okHttpClientBuilder: OkHttpClient.Builder) = builder.apply {
        baseUrl(propertyLoader.load("config.properties")["BACKEND_URL"].toString())
        client(okHttpClientBuilder.build())
        addConverterFactory(GsonConverterFactory.create((GsonBuilder().setLenient().create())))
    }.build()
}