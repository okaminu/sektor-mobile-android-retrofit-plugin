package lt.boldadmin.sektor.mobile.android.plugin.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.web.adapter.WorkLogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.web.service.WorkLogWebService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class WorkLogRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var workLogWebServiceMock: WorkLogWebService

    @Mock
    private lateinit var callMock: Call<Void>

    @Mock
    private lateinit var responseMock: Response<Void>

    @Test
    fun `Logs work by location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callMock).`when`(workLogWebServiceMock).logByLocation(eq(locationMock))
        doReturn(responseMock).`when`(callMock).execute()

        WorkLogRetrofitGatewayAdapter(
                "token",
                workLogWebServiceMock
        ).logByLocation(locationMock)

        verify(workLogWebServiceMock).logByLocation(same(locationMock))
        verify(callMock).execute()
    }
}