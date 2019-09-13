package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import lt.boldadmin.sektor.mobile.android.api.exception.CollaboratorNotFoundException
import lt.boldadmin.sektor.mobile.android.api.exception.IncorrectConfirmationCodeException
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.IdentityConfirmationRetrofitAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.IdentityConfirmationWebService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class IdentityConfirmationRetrofitAdapterTest {

    @Mock private lateinit var webServiceSpy: IdentityConfirmationWebService

    @Mock private lateinit var callStub: Call<Void>

    @Mock private lateinit var responseStub: Response<Void>

    private lateinit var gatewayAdapter: IdentityConfirmationRetrofitAdapter

    @BeforeEach
    fun setUp() {
        gatewayAdapter =
            IdentityConfirmationRetrofitAdapter(
                webServiceSpy
            )
    }

    @Test
    fun `Requests confirmation code`() {
        val mobileNumber = "+37066666666"
        doReturn(callStub).`when`(webServiceSpy).requestCode(eq(mobileNumber))
        doReturn(responseStub).`when`(callStub).execute()

        gatewayAdapter.requestCode(mobileNumber)

        verify(webServiceSpy).requestCode(same(mobileNumber))
        verify(callStub).execute()
    }

    @Test
    fun `Throws error when Collaborator by provided mobile number is not found`() {
        val mobileNumber = "+37066666666"
        doReturn(callStub).`when`(webServiceSpy).requestCode(eq(mobileNumber))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(404).`when`(responseStub).code()

        assertThrows(CollaboratorNotFoundException::class.java) {
            gatewayAdapter.requestCode(mobileNumber)
        }
    }

    @Test
    fun `Confirms received code`() {
        val confirmationCode = "123456"
        doReturn(callStub).`when`(webServiceSpy).confirmCode(eq(confirmationCode))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(CONFIRMATION_CODE).`when`(responseStub).body()

        val code = gatewayAdapter.confirmCode(confirmationCode)

        assertEquals(CONFIRMATION_CODE, code)
    }

    @Test
    fun `Throws error when confirmation code is incorrect`() {
        val confirmationCode = "123456"
        doReturn(callStub).`when`(webServiceSpy).confirmCode(eq(confirmationCode))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(401).`when`(responseStub).code()

        assertThrows(IncorrectConfirmationCodeException::class.java) {
            gatewayAdapter.confirmCode(confirmationCode)
        }
    }

    companion object {
        private val CONFIRMATION_CODE = "code"
    }
}
