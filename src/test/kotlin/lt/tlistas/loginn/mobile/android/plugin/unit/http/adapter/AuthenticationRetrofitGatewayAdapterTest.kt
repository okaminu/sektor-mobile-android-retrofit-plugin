package lt.tlistas.loginn.mobile.android.plugin.unit.http.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.loginn.mobile.android.plugin.http.adapter.AuthenticationRetrofitGatewayAdapter
import lt.tlistas.loginn.mobile.android.plugin.http.service.AuthenticationRetrofitService
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AuthenticationRetrofitGatewayAdapterTest {
    @Mock
    private lateinit var authenticationRetrofitServiceMock: AuthenticationRetrofitService

    @Mock
    private lateinit var callMock: Call<Void>

    @Mock
    private lateinit var responseMock: Response<Void>

    @Test
    fun `Registers mobile number`() {
        val mobileNumber = "+37066666666"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).registerMobileNumber(eq(mobileNumber))
        doReturn(responseMock).`when`(callMock).execute()

        AuthenticationRetrofitGatewayAdapter(authenticationRetrofitServiceMock).requestConfirmationCode(mobileNumber)

        verify(authenticationRetrofitServiceMock).registerMobileNumber(same(mobileNumber))
        verify(callMock).execute()
    }

    @Test
    fun `Confirms received code`() {
        val confirmationCode = "123456"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).confirmCode(eq(confirmationCode))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn("token").`when`(responseMock).body()

        val token = AuthenticationRetrofitGatewayAdapter(authenticationRetrofitServiceMock).confirmCode(confirmationCode)

        assertNotNull(token)
        verify(authenticationRetrofitServiceMock).confirmCode(same(confirmationCode))
        verify(callMock).execute()
    }
}