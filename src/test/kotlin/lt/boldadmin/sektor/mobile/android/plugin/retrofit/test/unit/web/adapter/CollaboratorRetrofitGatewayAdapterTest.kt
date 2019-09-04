package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.CollaboratorRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.CollaboratorWebService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CollaboratorRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var collaboratorWebServiceSpy: CollaboratorWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    @Mock
    private lateinit var responseDummy: Response<Void>

    @Test
    fun `Updates location using Retrofit`() {
        val locationMock = GpsCoordinates(15.0, 20.0)
        doReturn(callSpy).`when`(collaboratorWebServiceSpy).updateLocation(eq(locationMock))
        doReturn(responseDummy).`when`(callSpy).execute()

        CollaboratorRetrofitGatewayAdapter(
            "token", collaboratorWebServiceSpy
        ).updateLocation(locationMock)

        verify(collaboratorWebServiceSpy).updateLocation(same(locationMock))
        verify(callSpy).execute()
    }
}