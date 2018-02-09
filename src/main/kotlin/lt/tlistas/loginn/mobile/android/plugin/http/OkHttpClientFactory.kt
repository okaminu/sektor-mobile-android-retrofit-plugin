package lt.tlistas.loginn.mobile.android.plugin.http

import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit.SECONDS

class OkHttpClientFactory(private val propertyLoader: PropertyLoader = PropertyLoader(),
                          private val okHttpClient: OkHttpClient = OkHttpClient()) {

    fun client() = okHttpClient.newBuilder().apply {
        val prop = propertyLoader.load(TIMEOUT_PROPERTIES)
        readTimeout(getValue(prop, "READ_TIMEOUT"), SECONDS)
        writeTimeout(getValue(prop, "WRITE_TIMEOUT"), SECONDS)
        connectTimeout(getValue(prop, "CONNECT_TIMEOUT"), SECONDS)
    }.build()!!

    private fun getValue(prop: Properties, key: String) = prop[key].toString().toLong()

    companion object {
        const val TIMEOUT_PROPERTIES = "timeout.properties"
    }
}

