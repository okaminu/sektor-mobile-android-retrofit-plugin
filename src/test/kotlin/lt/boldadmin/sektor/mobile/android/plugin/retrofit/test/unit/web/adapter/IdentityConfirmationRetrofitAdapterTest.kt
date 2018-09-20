package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.boldadmin.sektor.mobile.android.api.exception.CollaboratorNotFoundException
import lt.boldadmin.sektor.mobile.android.api.exception.IncorrectConfirmationCodeException
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.IdentityConfirmationRetrofitAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.IdentityConfirmationWebService
import org.junit.Assert.assertEquals
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

    @Mock private lateinit var webServiceStub: IdentityConfirmationWebService

    @Mock private lateinit var callStub: Call<Void>

    @Mock private lateinit var responseStub: Response<Void>

    @Rule
    @JvmField
    val expectedException = ExpectedException.none()!!

    private lateinit var gatewayAdapter: IdentityConfirmationRetrofitAdapter

    @Before
    fun setUp() {
        gatewayAdapter =
            IdentityConfirmationRetrofitAdapter(
                webServiceStub
            )
    }

    @Test
    fun `Requests confirmation code`() {
        val mobileNumber = "+37066666666"
        doReturn(callStub).`when`(webServiceStub).requestCode(eq(mobileNumber))
        doReturn(responseStub).`when`(callStub).execute()

        gatewayAdapter.requestCode(mobileNumber)

        verify(webServiceStub).requestCode(same(mobileNumber))
        verify(callStub).execute()
    }

    @Test
    fun `Throws error when Collaborator by provided mobile number is not found`() {
        val mobileNumber = "+37066666666"
        expectedException.expect(CollaboratorNotFoundException::class.java)
        doReturn(callStub).`when`(webServiceStub).requestCode(eq(mobileNumber))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(404).`when`(responseStub).code()

        gatewayAdapter.requestCode(mobileNumber)
    }

    @Test
    fun `Confirms received code`() {
        val confirmationCode = "123456"
        doReturn(callStub).`when`(webServiceStub).confirmCode(eq(confirmationCode))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(CONFIRMATION_CODE).`when`(responseStub).body()

        val code = gatewayAdapter.confirmCode(confirmationCode)

        assertEquals(CONFIRMATION_CODE, code)
    }

    @Test
    fun `Throws error when confirmation code is incorrect`() {
        val confirmationCode = "123456"
        expectedException.expect(IncorrectConfirmationCodeException::class.java)
        doReturn(callStub).`when`(webServiceStub).confirmCode(eq(confirmationCode))
        doReturn(responseStub).`when`(callStub).execute()
        doReturn(401).`when`(responseStub).code()

        gatewayAdapter.confirmCode(confirmationCode)
    }

    companion object {
        private val CONFIRMATION_CODE = "code"
    }
}