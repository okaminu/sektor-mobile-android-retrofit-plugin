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
    private lateinit var statusWebServiceStub: StatusWebService

    @Mock
    private lateinit var callStub: Call<String>

    @Test
    fun `Gets backend status`() {
        doReturn(callStub).`when`(statusWebServiceStub).isHealthy()
        doReturn(Response.success(true)).`when`(callStub).execute()

        val status = StatusRetrofitGatewayAdapter(
            statusWebServiceStub
        ).isHealthy()

        assertEquals(true, status)
    }

}