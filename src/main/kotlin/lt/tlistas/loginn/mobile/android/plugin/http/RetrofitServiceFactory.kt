package lt.tlistas.loginn.mobile.android.plugin.http

import com.google.gson.GsonBuilder
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceFactory(private val propertyLoader: PropertyLoader = PropertyLoader(),
                             private val builder: Builder = Builder(),
                             private val client: OkHttpClient = OkHttpClientFactory().client()) {

    fun <S> buildService(serviceClass: Class<S>): S {
        return buildLoginnHttpClient().create(serviceClass)
    }

    private fun buildLoginnHttpClient() = builder.apply {
        baseUrl(propertyLoader.load("config.properties")["BACKEND_URL"].toString())
        client(client)
        addConverterFactory(GsonConverterFactory.create((GsonBuilder().setLenient().create())))
    }.build()
}