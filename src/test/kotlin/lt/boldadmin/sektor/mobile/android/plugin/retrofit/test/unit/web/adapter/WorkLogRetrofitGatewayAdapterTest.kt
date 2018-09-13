package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorkLogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkLogWebService
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
class WorkLogRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var workLogWebServiceSpy: WorkLogWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    @Mock
    private lateinit var responseDummy: Response<Void>

    @Test
    fun `Retrieves work log interval ids`() {
        val expectedIntervalIds = listOf("id1")
        doReturn(callSpy).`when`(workLogWebServiceSpy).getIntervalIdsByCollaborator()
        doReturn(Response.success(expectedIntervalIds)).`when`(callSpy).execute()

        val actualIntervalIds = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).getIntervalIdsByCollaborator()

        assertEquals(expectedIntervalIds, actualIntervalIds)
    }

    @Test
    fun `Retrieves project name of started work`() {
        val expectedProjectName = "ProjectName"

        val responseBody = ResponseBody.create(MediaType.parse(""), expectedProjectName)
        doReturn(callSpy).`when`(workLogWebServiceSpy).getProjectNameOfStartedWork()
        doReturn(Response.success(responseBody)).`when`(callSpy).execute()

        val actualProjectName = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).getProjectNameOfStartedWork()

        assertEquals(expectedProjectName, actualProjectName)
    }

    @Test
    fun `Retrieves work status`() {
        doReturn(callSpy).`when`(workLogWebServiceSpy).hasWorkStarted()
        doReturn(Response.success(true)).`when`(callSpy).execute()

        val workStatus = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).hasWorkStarted()

        assertTrue(workStatus)
    }

    @Test
    fun `Retrieves work log description`() {
        val intervalId = "id"
        val expectedDescription = "description"
        val responseBody = ResponseBody.create(MediaType.parse(""), expectedDescription)
        doReturn(callSpy).`when`(workLogWebServiceSpy).getDescription(intervalId)
        doReturn(Response.success(responseBody)).`when`(callSpy).execute()

        val actualDescription = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).getDescription(intervalId)

        assertEquals(expectedDescription, actualDescription)
    }
    
    @Test
    fun `Retrieves work log interval endpoints`() {
        val intervalId = "id"
        val workLogIntervalEndpointsDummy = mock<WorkLogIntervalEndpoints>()
        doReturn(callSpy).`when`(workLogWebServiceSpy).getIntervalEndpoints(intervalId)
        doReturn(Response.success(workLogIntervalEndpointsDummy)).`when`(callSpy).execute()

        val workLogIntervalEndpoints = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).getIntervalEndpoints(intervalId)

        assertSame(workLogIntervalEndpointsDummy, workLogIntervalEndpoints)
    }

    @Test
    fun `Retrieves work durations sum of several work logs`() {
        val intervalIds = listOf("id1", "id2")
        val expectedDurationsSum = 1000L
        doReturn(callSpy).`when`(workLogWebServiceSpy).getDurationsSum("id1,id2")
        doReturn(Response.success(expectedDurationsSum)).`when`(callSpy).execute()

        val actualDurationsSum = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).getDurationsSum(intervalIds)

        assertEquals(expectedDurationsSum, actualDurationsSum)
    }

    @Test
    fun `Logs work by location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callSpy).`when`(workLogWebServiceSpy).logByLocation(eq(locationMock))
        doReturn(responseDummy).`when`(callSpy).execute()

        WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).logByLocation(locationMock)

        verify(workLogWebServiceSpy).logByLocation(same(locationMock))
        verify(callSpy).execute()
    }

    @Test
    fun `Updates work log description`() {
        val intervalId = "intervalId"
        val description = "description"
        doReturn(callSpy)
            .`when`(workLogWebServiceSpy)
            .updateDescription(eq(intervalId), any())

        doReturn(responseDummy).`when`(callSpy).execute()

        WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceSpy
        ).updateDescription(intervalId, description)

        argumentCaptor<RequestBody>().apply {
            verify(workLogWebServiceSpy).updateDescription(eq(intervalId), capture())
            verify(callSpy).execute()

            Buffer().also {
                firstValue.writeTo(it)
                assertEquals(description, it.readUtf8())
            }
        }
    }

}