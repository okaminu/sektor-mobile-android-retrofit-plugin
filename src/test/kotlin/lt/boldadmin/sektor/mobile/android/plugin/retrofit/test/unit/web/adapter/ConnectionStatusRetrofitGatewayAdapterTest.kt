package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.doReturn
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.ConnectionStatusRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ConnectionStatusWebService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class ConnectionStatusRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var webServiceStub: ConnectionStatusWebService

    @Mock
    private lateinit var callStub: Call<String>

    @Test
    fun `Request backend health status`() {
        doReturn(callStub).`when`(webServiceStub).isHealthy()
        doReturn(Response.success(true)).`when`(callStub).execute()

        val status = ConnectionStatusRetrofitGatewayAdapter(
            webServiceStub
        ).isHealthy()

        assertEquals(true, status)
    }

}
