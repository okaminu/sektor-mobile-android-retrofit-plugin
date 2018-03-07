package lt.tlistas.loginn.mobile.android.plugin.unit.http

import com.nhaarman.mockito_kotlin.*
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.interceptor.AuthenticationInterceptor
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkTimeRetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit.Builder
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class RetrofitServiceFactoryTest {

    @Mock
    private lateinit var retrofitBuilderMock: Builder

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Mock
    private lateinit var okHttpBuilderMock: OkHttpClient.Builder

    @Test
    fun `Gets Retrofit service without token`() {
        doReturn(mock<OkHttpClient>()).`when`(okHttpBuilderMock).build()
        mockRetrofitBuilder()

        val retrofitService = RetrofitServiceFactory(propertyLoaderMock, retrofitBuilderMock, okHttpBuilderMock)
                .buildService(WorkTimeRetrofitService::class.java)

        verify(okHttpBuilderMock, never()).interceptors()
        verify(retrofitBuilderMock).addConverterFactory(any())
        verify(retrofitBuilderMock).baseUrl(any<String>())
        verify(retrofitBuilderMock).client(any())
        verify(retrofitBuilderMock).build()
        verify(propertyLoaderMock).load(any())
        assertNotNull(retrofitService)
    }

    @Test
    fun `Gets Retrofit service with token`() {
        val interceptorsMock = mock<ArrayList<Interceptor>>()
        mockRetrofitBuilder()
        doReturn(interceptorsMock).`when`(okHttpBuilderMock).interceptors()
        doReturn(true).`when`(interceptorsMock).add(any())
        doReturn(mock<OkHttpClient>()).`when`(okHttpBuilderMock).build()

        val retrofitService = RetrofitServiceFactory(propertyLoaderMock, retrofitBuilderMock, okHttpBuilderMock)
                .buildService(WorkTimeRetrofitService::class.java, "token")

        verify(okHttpBuilderMock).interceptors()
        verify(interceptorsMock).add(any<AuthenticationInterceptor>())
        verify(okHttpBuilderMock).build()
        verify(retrofitBuilderMock).addConverterFactory(any())
        verify(retrofitBuilderMock).baseUrl(any<String>())
        verify(retrofitBuilderMock).client(any())
        verify(retrofitBuilderMock).build()
        verify(propertyLoaderMock).load(any())
        assertNotNull(retrofitService)
    }

    private fun mockRetrofitBuilder() {
        val retrofitMock = Builder().baseUrl("http://www.test.com").build()
        doReturn(retrofitMock).`when`(retrofitBuilderMock).build()
        val propertyMock = mock<Properties>()
        doReturn("URL").`when`(propertyMock)[any()]
        doReturn(propertyMock).`when`(propertyLoaderMock).load(any())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).addConverterFactory(any())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).baseUrl(any<String>())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).client(any())
    }
}