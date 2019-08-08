package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.doReturn
import lt.boldadmin.sektor.mobile.android.api.valueobject.WorkTime
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorkTimeRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkTimeWebService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class WorkTimeRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var webServiceStub: WorkTimeWebService

    @Mock
    private lateinit var callMock: Call<WorkTime>

    @Test
    fun `Gets work time`() {
        val workTimeRetrofitGatewayAdapter = WorkTimeRetrofitGatewayAdapter("token", webServiceStub)
        val workTimeMock = WorkTime()
        val responseMock = Response.success(WorkTime())
        doReturn(callMock).`when`(webServiceStub).getWorkTime()
        doReturn(responseMock).`when`(callMock).execute()

        val workTime = workTimeRetrofitGatewayAdapter.getWorkTime()

        assertEquals(workTimeMock, workTime)
    }
}