package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.boldadmin.sektor.mobile.android.api.exception.CollaboratorNotFoundException
import lt.boldadmin.sektor.mobile.android.api.exception.IncorrectConfirmationCodeException
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.IdentityConfirmationRetrofitAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.IdentityConfirmationWebService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class IdentityConfirmationRetrofitAdapterTest {

    @Mock private lateinit var webClientMock: IdentityConfirmationWebService

    @Mock private lateinit var callMock: Call<Void>

    @Mock private lateinit var responseMock: Response<Void>

    @Rule
    @JvmField
    val expectedException = ExpectedException.none()!!

    private lateinit var gatewayAdapter: IdentityConfirmationRetrofitAdapter

    @Before
    fun setUp() {
        gatewayAdapter =
            IdentityConfirmationRetrofitAdapter(
                webClientMock
            )
    }

    @Test
    fun `Requests confirmation code`() {
        val mobileNumber = "+37066666666"
        doReturn(callMock).`when`(webClientMock).requestCode(eq(mobileNumber))
        doReturn(responseMock).`when`(callMock).execute()

        gatewayAdapter.requestCode(mobileNumber)

        verify(webClientMock).requestCode(same(mobileNumber))
        verify(callMock).execute()
    }

    @Test
    fun `Throws error when Collaborator by provided mobile number is not found`() {
        val mobileNumber = "+37066666666"
        expectedException.expect(CollaboratorNotFoundException::class.java)
        doReturn(callMock).`when`(webClientMock).requestCode(eq(mobileNumber))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn(404).`when`(responseMock).code()

        gatewayAdapter.requestCode(mobileNumber)
    }

    @Test
    fun `Confirms received code`() {
        val confirmationCode = "123456"
        doReturn(callMock).`when`(webClientMock).confirmCode(eq(confirmationCode))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn("token").`when`(responseMock).body()

        val token = gatewayAdapter.confirmCode(confirmationCode)

        assertNotNull(token)
        verify(webClientMock).confirmCode(same(confirmationCode))
        verify(callMock).execute()
    }

    @Test
    fun `Throws error when confirmation code is incorrect`() {
        val confirmationCode = "123456"
        expectedException.expect(IncorrectConfirmationCodeException::class.java)
        doReturn(callMock).`when`(webClientMock).confirmCode(eq(confirmationCode))
        doReturn(responseMock).`when`(callMock).execute()
        doReturn(401).`when`(responseMock).code()

        gatewayAdapter.confirmCode(confirmationCode)
    }
}