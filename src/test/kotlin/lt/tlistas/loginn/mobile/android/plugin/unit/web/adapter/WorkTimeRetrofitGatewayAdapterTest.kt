package lt.tlistas.loginn.mobile.android.plugin.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import lt.tlistas.loginn.mobile.android.api.type.valueobject.WorkTime
import lt.tlistas.loginn.mobile.android.plugin.web.adapter.WorkTimeRetrofitGatewayAdapter
import lt.tlistas.loginn.mobile.android.plugin.web.service.WorkTimeWebService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class WorkTimeRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var workTimeWebServiceMock: WorkTimeWebService

    @Mock
    private lateinit var callMock: Call<WorkTime>

    @Test
    fun `Gets work time`() {
        val workTimeRetrofitGatewayAdapter = WorkTimeRetrofitGatewayAdapter("token", workTimeWebServiceMock)
        val workTimeMock = WorkTime()
        val responseMock = Response.success(WorkTime())
        doReturn(callMock).`when`(workTimeWebServiceMock).getWorkTime()
        doReturn(responseMock).`when`(callMock).execute()

        val workTime = workTimeRetrofitGatewayAdapter.getWorkTime()

        assertEquals(workTimeMock, workTime)
    }
}