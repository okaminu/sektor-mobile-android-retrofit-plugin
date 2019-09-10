package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.*
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.WorklogRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorklogWebService
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class WorklogRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var worklogWebServiceSpy: WorklogWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    @Mock
    private lateinit var responseDummy: Response<Void>

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
