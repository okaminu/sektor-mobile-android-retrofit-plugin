package lt.tlistas.loginn.mobile.android.plugin.unit.web.interceptor

import com.nhaarman.mockito_kotlin.*
import lt.tlistas.loginn.mobile.android.plugin.web.interceptor.AuthenticationInterceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import org.junit.Assert.assertNotNull
import org.junit.Test

class AuthenticationInterceptorTest {

    @Test
    fun `Adds authentication token to header`() {
        val chainMock = mock<Chain>()
        val chainRequestMock = mock<Request>()
        val okHttpBuilderMock = mock<Builder>()
        doReturn(chainRequestMock).`when`(chainMock).request()
        doReturn(okHttpBuilderMock).`when`(chainRequestMock).newBuilder()
        doReturn(okHttpBuilderMock).`when`(okHttpBuilderMock).header(any(), any())
        doReturn(mock<Request>()).`when`(okHttpBuilderMock).build()
        doReturn(mock<Response>()).`when`(chainMock).proceed(any())

        val response = AuthenticationInterceptor("token").intercept(chainMock)

        assertNotNull(response)
        verify(chainMock).request()
        verify(chainRequestMock).newBuilder()
        verify(okHttpBuilderMock).header(any(), eq("token"))
        verify(okHttpBuilderMock).build()
        verify(chainMock).proceed(any())
    }
}