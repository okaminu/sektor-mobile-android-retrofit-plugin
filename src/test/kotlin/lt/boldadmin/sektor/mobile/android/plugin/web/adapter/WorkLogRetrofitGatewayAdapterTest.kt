package lt.boldadmin.sektor.mobile.android.plugin.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
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

    @Test
    fun `Retrieves project name of started work`() {
        val projectName = "ProjectName"

        doReturn(callMock).`when`(workLogWebServiceMock).getProjectNameOfStartedWork()
        doReturn(Response.success(projectName)).`when`(callMock).execute()

        val projectNameOfStartedWork = WorkLogRetrofitGatewayAdapter(
                "token",
                workLogWebServiceMock
        ).getProjectNameOfStartedWork()

        assertEquals(projectName, projectNameOfStartedWork)
        verify(workLogWebServiceMock).getProjectNameOfStartedWork()
        verify(callMock).execute()
    }

    @Test
    fun `Retrieves work status`() {
        doReturn(callMock).`when`(workLogWebServiceMock).hasWorkStarted()
        doReturn(Response.success(true)).`when`(callMock).execute()

        val workStatus = WorkLogRetrofitGatewayAdapter(
                "token",
                workLogWebServiceMock
        ).hasWorkStarted()

        assertTrue(workStatus)
        verify(workLogWebServiceMock).hasWorkStarted()
        verify(callMock).execute()
    }
}