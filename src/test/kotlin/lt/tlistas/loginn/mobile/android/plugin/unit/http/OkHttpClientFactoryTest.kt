package lt.tlistas.loginn.mobile.android.plugin.unit.http

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.loginn.mobile.android.plugin.PropertyLoader
import lt.tlistas.loginn.mobile.android.plugin.http.OkHttpClientBuilder
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class OkHttpClientFactoryTest {

    @Mock
    private lateinit var okHttpClientMock: OkHttpClient

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Test
    fun `Sets default builder parameters`() {
        val builderMock = mock<OkHttpClient.Builder>()
        val propertyMock = mock<Properties>()
        doReturn(10).`when`(propertyMock)[any()]
        doReturn(propertyMock).`when`(propertyLoaderMock).load(any())
        mockOkHttpClientBuilder(builderMock)

        val client = OkHttpClientBuilder(propertyLoaderMock, okHttpClientMock).defaultBuilder()

        verify(okHttpClientMock).newBuilder()
        verify(builderMock).readTimeout(any(), any())
        verify(builderMock).writeTimeout(any(), any())
        verify(builderMock).connectTimeout(any(), any())
        verify(propertyLoaderMock).load(any())
        assertNotNull(client)
    }

    private fun mockOkHttpClientBuilder(builderMock: OkHttpClient.Builder) {
        doReturn(builderMock).`when`(okHttpClientMock).newBuilder()
        doReturn(builderMock).`when`(builderMock).readTimeout(any(), any())
        doReturn(builderMock).`when`(builderMock).writeTimeout(any(), any())
        doReturn(builderMock).`when`(builderMock).connectTimeout(any(), any())
    }
}