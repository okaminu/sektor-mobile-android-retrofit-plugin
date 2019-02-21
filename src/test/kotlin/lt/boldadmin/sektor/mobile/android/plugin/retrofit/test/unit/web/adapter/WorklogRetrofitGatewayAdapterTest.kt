package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorklogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorklogWebService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class WorklogRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var worklogWebServiceSpy: WorklogWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    @Mock
    private lateinit var responseDummy: Response<Void>

    @Test
    fun `Retrieves work log interval ids`() {
        val expectedIntervalIds = listOf("id1")
        doReturn(callSpy).`when`(worklogWebServiceSpy).getIntervalIdsByCollaborator()
        doReturn(Response.success(expectedIntervalIds)).`when`(callSpy).execute()

        val actualIntervalIds = WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).getIntervalIdsByCollaborator()

        assertEquals(expectedIntervalIds, actualIntervalIds)
    }

    @Test
    fun `Retrieves project name of started work`() {
        val expectedProjectName = "ProjectName"

        val responseBody = ResponseBody.create(MediaType.parse(""), expectedProjectName)
        doReturn(callSpy).`when`(worklogWebServiceSpy).getProjectNameOfStartedWork()
        doReturn(Response.success(responseBody)).`when`(callSpy).execute()

        val actualProjectName = WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).getProjectNameOfStartedWork()

        assertEquals(expectedProjectName, actualProjectName)
    }

    @Test
    fun `Retrieves work status`() {
        doReturn(callSpy).`when`(worklogWebServiceSpy).hasWorkStarted()
        doReturn(Response.success(true)).`when`(callSpy).execute()

        val workStatus = WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).hasWorkStarted()

        assertTrue(workStatus)
    }

    @Test
    fun `Retrieves work log interval endpoints`() {
        val intervalId = "id"
        val workLogIntervalEndpointsDummy = mock<WorkLogIntervalEndpoints>()
        doReturn(callSpy).`when`(worklogWebServiceSpy).getIntervalEndpoints(intervalId)
        doReturn(Response.success(workLogIntervalEndpointsDummy)).`when`(callSpy).execute()

        val workLogIntervalEndpoints = WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).getIntervalEndpoints(intervalId)

        assertSame(workLogIntervalEndpointsDummy, workLogIntervalEndpoints)
    }

    @Test
    fun `Retrieves work durations sum of several work logs`() {
        val intervalIds = listOf("id1", "id2")
        val expectedDurationsSum = 1000L
        doReturn(callSpy).`when`(worklogWebServiceSpy).getDurationsSum("id1,id2")
        doReturn(Response.success(expectedDurationsSum)).`when`(callSpy).execute()

        val actualDurationsSum = WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).getDurationsSum(intervalIds)

        assertEquals(expectedDurationsSum, actualDurationsSum)
    }

    @Test
    fun `Logs work by location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callSpy).`when`(worklogWebServiceSpy).logByLocation(eq(locationMock))
        doReturn(responseDummy).`when`(callSpy).execute()

        WorklogRetrofitGatewayAdapter(
            "token", worklogWebServiceSpy
        ).logByLocation(locationMock)

        verify(worklogWebServiceSpy).logByLocation(same(locationMock))
        verify(callSpy).execute()
    }
}