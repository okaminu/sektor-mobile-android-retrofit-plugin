package lt.tlistas.loginn.mobile.android.plugin.http.service

import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WorkLogRetrofitService {

    @POST("/collaborator/update")
    fun logWorkByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

}