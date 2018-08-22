package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.StatusRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.StatusWebService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class StatusRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var statusWebServiceSpy: StatusWebService

    @Mock
    private lateinit var callMock: Call<String>

    @Test
    fun `Gets backend status`() {
        doReturn(callMock).`when`(statusWebServiceSpy).getStatus()
        doReturn(Response.success(RESPONSE_STATUS)).`when`(callMock).execute()

        val status = StatusRetrofitGatewayAdapter(
            statusWebServiceSpy
        ).status()

        assertEquals(RESPONSE_STATUS, status)
    }

    companion object {
        private val RESPONSE_STATUS = "OK"
    }

}