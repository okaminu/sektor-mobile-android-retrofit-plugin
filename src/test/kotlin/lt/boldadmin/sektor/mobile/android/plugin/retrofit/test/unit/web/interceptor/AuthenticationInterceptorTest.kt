package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.interceptor

import com.nhaarman.mockitokotlin2.*
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.interceptor.AuthenticationInterceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import org.junit.Assert.assertNotNull
import org.junit.Test

class AuthenticationInterceptorTest {

    @Test
    fun `Adds authentication token to header`() {
        val chainSpy: Chain = mock()
        val chainRequestSpy: Request = mock()
        val okHttpBuilderSpy: Builder = mock()
        doReturn(chainRequestSpy).`when`(chainSpy).request()
        doReturn(okHttpBuilderSpy).`when`(chainRequestSpy).newBuilder()
        doReturn(okHttpBuilderSpy).`when`(okHttpBuilderSpy).header(any(), any())
        doReturn(mock<Request>()).`when`(okHttpBuilderSpy).build()
        doReturn(mock<Response>()).`when`(chainSpy).proceed(any())

        val response = AuthenticationInterceptor("token").intercept(chainSpy)

        assertNotNull(response)
        verify(chainSpy).request()
        verify(chainRequestSpy).newBuilder()
        verify(okHttpBuilderSpy).header(any(), eq("token"))
        verify(okHttpBuilderSpy).build()
        verify(chainSpy).proceed(any())
    }
}