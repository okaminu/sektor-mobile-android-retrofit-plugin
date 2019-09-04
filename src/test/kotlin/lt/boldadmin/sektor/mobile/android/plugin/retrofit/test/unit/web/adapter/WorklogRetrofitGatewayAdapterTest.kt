package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorklogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorklogWebService
import okhttp3.MediaType
import okhttp3.ResponseBody
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
}