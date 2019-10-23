package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.api.valueobject.WorkTime
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.CollaboratorRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.CollaboratorWebService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class CollaboratorRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var collaboratorWebServiceSpy: CollaboratorWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    private lateinit var adapter: CollaboratorRetrofitGatewayAdapter

    @BeforeEach
    fun `Set up`() {
        adapter = CollaboratorRetrofitGatewayAdapter("token", collaboratorWebServiceSpy)
    }

    @Test
    fun `Updates coordinates using Retrofit`() {
        val responseDummy: Response<Void> = mock()
        val coordinates = GpsCoordinates(15.0, 20.0)
        doReturn(callSpy).`when`(collaboratorWebServiceSpy).updateCoordinates(coordinates)
        doReturn(responseDummy).`when`(callSpy).execute()

        adapter.updateCoordinates(coordinates)

        verify(collaboratorWebServiceSpy).updateCoordinates(same(coordinates))
        verify(callSpy).execute()
    }

    @Test
    fun `Gets work time`() {
        val expectedWorkTime = WorkTime()
        doReturn(callSpy).`when`(collaboratorWebServiceSpy).getWorkTime()
        doReturn(Response.success(expectedWorkTime)).`when`(callSpy).execute()

        val actualWorkTime = adapter.getWorkTime()

        assertEquals(expectedWorkTime, actualWorkTime)
    }
}
