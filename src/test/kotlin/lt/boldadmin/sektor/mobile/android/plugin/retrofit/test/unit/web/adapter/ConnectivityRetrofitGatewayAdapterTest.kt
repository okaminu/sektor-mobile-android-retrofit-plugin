package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.ConnectivityRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ConnectivityWebService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class ConnectivityRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var connectivityWebServiceStub: ConnectivityWebService

    @Mock
    private lateinit var callStub: Call<String>

    @Test
    fun `Request backend health state`() {
        doReturn(callStub).`when`(connectivityWebServiceStub).isHealthy()
        doReturn(Response.success(true)).`when`(callStub).execute()

        val status = ConnectivityRetrofitGatewayAdapter(
            connectivityWebServiceStub
        ).isHealthy()

        assertEquals(true, status)
    }

}