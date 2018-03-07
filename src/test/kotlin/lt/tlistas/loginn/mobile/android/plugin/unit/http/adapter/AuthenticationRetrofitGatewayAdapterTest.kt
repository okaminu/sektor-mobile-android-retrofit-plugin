package lt.tlistas.loginn.mobile.android.plugin.unit.http.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.loginn.backend.exception.AuthenticationException
import lt.tlistas.loginn.backend.exception.CollaboratorNotFoundException
import lt.tlistas.loginn.mobile.android.plugin.http.adapter.AuthenticationRetrofitGatewayAdapter
import lt.tlistas.loginn.mobile.android.plugin.http.service.AuthenticationRetrofitService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AuthenticationRetrofitGatewayAdapterTest {

    @Mock private lateinit var authenticationRetrofitServiceMock: AuthenticationRetrofitService

    @Mock private lateinit var callMock: Call<Void>

    @Mock private lateinit var responseMock: Response<Void>

    private lateinit var gatewayAdapter: AuthenticationRetrofitGatewayAdapter

    @Before
    fun setUp(){
        gatewayAdapter = AuthenticationRetrofitGatewayAdapter(authenticationRetrofitServiceMock)
    }

    @Test
    fun `Registers mobile number`() {
        val mobileNumber = "+37066666666"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).registerMobileNumber(eq(mobileNumber))
        doReturn(responseMock).`when`(callMock).execute()

        gatewayAdapter.requestConfirmationCode(mobileNumber)

        verify(authenticationRetrofitServiceMock).registerMobileNumber(same(mobileNumber))
        verify(callMock).execute()
    }

    @Test(expected = CollaboratorNotFoundException::class)
    fun `Throws error when Collaborator by provided mobile number is not found`() {
        val mobileNumber = "+37066666666"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).registerMobileNumber(eq(mobileNumber))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn(404).`when`(responseMock).code()

        gatewayAdapter.requestConfirmationCode(mobileNumber)
    }

    @Test
    fun `Confirms received code`() {
        val confirmationCode = "123456"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).confirmCode(eq(confirmationCode))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn("token").`when`(responseMock).body()

        val token = gatewayAdapter.confirmCode(confirmationCode)

        assertNotNull(token)
        verify(authenticationRetrofitServiceMock).confirmCode(same(confirmationCode))
        verify(callMock).execute()
    }

    @Test(expected = AuthenticationException::class)
    fun `Throws error when confirmation code is incorrect`() {
        val confirmationCode = "123456"
        doReturn(callMock).`when`(authenticationRetrofitServiceMock).confirmCode(eq(confirmationCode))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn(404).`when`(responseMock).code()

        gatewayAdapter.confirmCode(confirmationCode)
    }
}