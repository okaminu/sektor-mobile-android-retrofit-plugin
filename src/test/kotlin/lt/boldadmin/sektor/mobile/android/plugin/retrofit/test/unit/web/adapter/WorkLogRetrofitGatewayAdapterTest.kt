package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorkLogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkLogWebService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `Retrieves work log interval ids`() {
        val expectedIntervalIds = listOf("id1")
        doReturn(callMock).`when`(workLogWebServiceMock).getIntervalIdsByCollaborator()
        doReturn(Response.success(expectedIntervalIds)).`when`(callMock).execute()

        val actualIntervalIds = WorkLogRetrofitGatewayAdapter("token", workLogWebServiceMock).getIntervalIdsByCollaborator()

        assertEquals(expectedIntervalIds, actualIntervalIds)
    }

    @Test
    fun `Retrieves project name of started work`() {
        val expectedProjectName = "ProjectName"

        val responseBody = ResponseBody.create(MediaType.parse(""), expectedProjectName)
        doReturn(callMock).`when`(workLogWebServiceMock).getProjectNameOfStartedWork()
        doReturn(Response.success(responseBody)).`when`(callMock).execute()

        val actualProjectName = WorkLogRetrofitGatewayAdapter(
            "token",
            workLogWebServiceMock
        )
            .getProjectNameOfStartedWork()

        assertEquals(expectedProjectName, actualProjectName)
    }

    @Test
    fun `Retrieves work status`() {
        doReturn(callMock).`when`(workLogWebServiceMock).hasWorkStarted()
        doReturn(Response.success(true)).`when`(callMock).execute()

        val workStatus = WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceMock
        ).hasWorkStarted()

        assertTrue(workStatus)
    }

    @Test
    fun `Retrieves work log description`() {
        val intervalId = "id"
        val expectedDescription = "description"
        val responseBody = ResponseBody.create(MediaType.parse(""), expectedDescription)
        doReturn(callMock).`when`(workLogWebServiceMock).getDescription(eq(intervalId))
        doReturn(Response.success(responseBody)).`when`(callMock).execute()

        val actualDescription = WorkLogRetrofitGatewayAdapter("token", workLogWebServiceMock).getDescription(intervalId)

        assertEquals(expectedDescription, actualDescription)
    }

    @Test
    fun `Retrieves durations sums for several work logs`() {
        val intervalIds = listOf("id1", "id2")
        val expectedDurationsSum = 1000L
        doReturn(callMock).`when`(workLogWebServiceMock).getDurationsSum("id1,id2")
        doReturn(Response.success(expectedDurationsSum)).`when`(callMock).execute()

        val actualDurationsSum = WorkLogRetrofitGatewayAdapter("token", workLogWebServiceMock).getDurationsSum(intervalIds)

        assertEquals(expectedDurationsSum, actualDurationsSum)
    }

    @Test
    fun `Logs work by location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callMock).`when`(workLogWebServiceMock).logByLocation(eq(locationMock))
        doReturn(responseMock).`when`(callMock).execute()

        WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceMock
        ).logByLocation(locationMock)

        verify(workLogWebServiceMock).logByLocation(same(locationMock))
        verify(callMock).execute()
    }

    @Test
    fun `Updates work log description`() {
        val intervalId = "intervalId"
        val description = "description"
        doReturn(callMock)
            .`when`(workLogWebServiceMock)
            .updateDescription(eq(intervalId), any())

        doReturn(responseMock).`when`(callMock).execute()

        WorkLogRetrofitGatewayAdapter(
            "token", workLogWebServiceMock
        ).updateDescription(intervalId, description)

        argumentCaptor<RequestBody>().apply {
            verify(workLogWebServiceMock).updateDescription(eq(intervalId), capture())
            verify(callMock).execute()

            Buffer().also {
                firstValue.writeTo(it)
                assertEquals(description, it.readUtf8())
            }
        }
    }
    
}