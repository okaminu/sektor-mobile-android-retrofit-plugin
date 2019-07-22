package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.ConnectionStatusRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ConnectionStatusWebService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
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
