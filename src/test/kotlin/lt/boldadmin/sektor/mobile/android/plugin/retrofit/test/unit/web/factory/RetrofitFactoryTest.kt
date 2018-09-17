package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.factory

import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.PropertyLoader
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.interceptor.AuthenticationInterceptor
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkTimeWebService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class RetrofitFactoryTest {

    @Mock
    private lateinit var retrofitBuilderSpy: Builder

    @Mock
    private lateinit var propertyLoaderSpy: PropertyLoader

    @Mock
    private lateinit var okHttpClientSpy: OkHttpClient

    @Mock
    private lateinit var okHttpBuilderSpy: OkHttpClient.Builder

    private lateinit var retrofitFactory: RetrofitFactory

    @Before
    fun `Set up`() {
        mockOkHttpClientBuilder()
        retrofitFactory = RetrofitFactory(propertyLoaderSpy, retrofitBuilderSpy, okHttpClientSpy)
    }

    @Test
    fun `Gets Retrofit service without token`() {
        mockOkHttpClientBuilder()
        mockRetrofitBuilder()

        retrofitFactory.create(WorkTimeWebService::class.java)

        verifyOkHttpBuilderMock()
        verify(okHttpClientSpy, never()).interceptors()
        verify(retrofitBuilderSpy).client(okHttpClientSpy)
        verify(retrofitBuilderSpy).addConverterFactory(any())
        verify(retrofitBuilderSpy).baseUrl(any<String>())
        verify(retrofitBuilderSpy).build()
        verify(propertyLoaderSpy, times(2)).load(any())
        assertNotNull(retrofitFactory)
    }

    @Test
    fun `Gets Retrofit service with token`() {
        val interceptorsMock = mock<ArrayList<Interceptor>>()
        mockRetrofitBuilder()
        mockOkHttpClientBuilder()
        doReturn(interceptorsMock).`when`(okHttpBuilderSpy).interceptors()
        doReturn(true).`when`(interceptorsMock).add(any())

        retrofitFactory.create(WorkTimeWebService::class.java, "token")

        verifyOkHttpBuilderMock()
        verify(okHttpBuilderSpy).interceptors()
        verify(interceptorsMock).add(any<AuthenticationInterceptor>())
        verify(retrofitBuilderSpy).client(okHttpClientSpy)
        verify(retrofitBuilderSpy).addConverterFactory(any())
        verify(retrofitBuilderSpy).baseUrl(any<String>())
        verify(retrofitBuilderSpy).build()
        verify(propertyLoaderSpy, times(2)).load(any())
        assertNotNull(retrofitFactory)
    }

    private fun mockOkHttpClientBuilder() {
        val propertyMock = mock<Properties>()
        doReturn(10).`when`(propertyMock)[any()]
        doReturn(propertyMock).`when`(propertyLoaderSpy).load(eq("timeout.properties"))
        doReturn(okHttpBuilderSpy).`when`(okHttpClientSpy).newBuilder()
        doReturn(okHttpBuilderSpy).`when`(okHttpBuilderSpy).readTimeout(any(), any())
        doReturn(okHttpBuilderSpy).`when`(okHttpBuilderSpy).writeTimeout(any(), any())
        doReturn(okHttpBuilderSpy).`when`(okHttpBuilderSpy).connectTimeout(any(), any())
        doReturn(okHttpClientSpy).`when`(okHttpBuilderSpy).build()
    }

    private fun mockRetrofitBuilder() {
        val retrofitMock = mock<Retrofit>()
        doReturn(retrofitMock).`when`(retrofitBuilderSpy).build()
        val propertyMock = mock<Properties>()
        doReturn("URL").`when`(propertyMock)[any()]
        doReturn(propertyMock).`when`(propertyLoaderSpy).load(eq("config.properties"))
        doReturn(retrofitBuilderSpy).`when`(retrofitBuilderSpy).addConverterFactory(any())
        doReturn(retrofitBuilderSpy).`when`(retrofitBuilderSpy).baseUrl(any<String>())
    }

    private fun verifyOkHttpBuilderMock() {
        verify(okHttpClientSpy).newBuilder()
        verify(okHttpBuilderSpy).readTimeout(any(), any())
        verify(okHttpBuilderSpy).writeTimeout(any(), any())
        verify(okHttpBuilderSpy).connectTimeout(any(), any())
    }
}