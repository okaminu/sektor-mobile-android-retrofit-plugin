package lt.tlistas.loginn.mobile.android.plugin.web.service

import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WorkLogWebService {

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

}