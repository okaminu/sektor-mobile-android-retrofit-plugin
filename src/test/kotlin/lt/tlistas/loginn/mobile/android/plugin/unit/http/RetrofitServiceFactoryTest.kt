package lt.tlistas.loginn.mobile.unit.http

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkTimeRetrofitService
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

    @Test
    fun `Gets Retrofit service`() {
        val propertyMock = mock<Properties>()
        val retrofitMock = Builder().baseUrl("http://www.test.com").build()

        doReturn("URL").`when`(propertyMock)[any()]
        doReturn(propertyMock).`when`(propertyLoaderMock).load(any())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).addConverterFactory(any())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).baseUrl(any<String>())
        doReturn(retrofitBuilderMock).`when`(retrofitBuilderMock).client(any())
        doReturn(retrofitMock).`when`(retrofitBuilderMock).build()

        val retrofitService = RetrofitServiceFactory(propertyLoaderMock, retrofitBuilderMock, mock())
                .buildService(WorkTimeRetrofitService::class.java)

        verify(retrofitBuilderMock).addConverterFactory(any())
        verify(retrofitBuilderMock).baseUrl(any<String>())
        verify(retrofitBuilderMock).client(any())
        verify(retrofitBuilderMock).build()
        verify(propertyLoaderMock).load(any())
        assertNotNull(retrofitService)
    }
}