package lt.tlistas.loginn.mobile.unit.http.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import lt.tlistas.loginn.mobile.android.plugin.http.adapter.WorkLogRetrofitGatewayAdapter
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkLogRetrofitService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class WorkLogRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var workLogRetrofitServiceMock: WorkLogRetrofitService

    @Mock
    private lateinit var callMock: Call<Void>

    @Mock
    private lateinit var responseMock: Response<Void>

    @Test
    fun `Logs work by location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callMock).`when`(workLogRetrofitServiceMock).logWorkByLocation(eq(locationMock))
        doReturn(responseMock).`when`(callMock).execute()

        WorkLogRetrofitGatewayAdapter(workLogRetrofitServiceMock).logWorkByLocation(locationMock)

        verify(workLogRetrofitServiceMock).logWorkByLocation(same(locationMock))
        verify(callMock).execute()
    }
}