package lt.tlistas.loginn.mobile.unit.http.adapter

import com.nhaarman.mockito_kotlin.doReturn
import lt.tlistas.loginn.mobile.android.api.type.valueobject.WorkTime
import lt.tlistas.loginn.mobile.android.plugin.http.adapter.WorkTimeRetrofitGatewayAdapter
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkTimeRetrofitService
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
    private lateinit var workTimeRetrofitServiceMock: WorkTimeRetrofitService

    @Mock
    private lateinit var callMock: Call<WorkTime>

    @Test
    fun `Gets work time`() {
        val workTimeRetrofitGatewayAdapter = WorkTimeRetrofitGatewayAdapter("token", workTimeRetrofitServiceMock)
        val workTimeMock = WorkTime()
        val responseMock = Response.success(WorkTime())
        doReturn(callMock).`when`(workTimeRetrofitServiceMock).getWorkTime()
        doReturn(responseMock).`when`(callMock).execute()

        val workTime = workTimeRetrofitGatewayAdapter.getWorkTime()

        assertEquals(workTimeMock, workTime)
    }
}