package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.api.valueobject.WorkTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CollaboratorWebService {

    @POST("/collaborator/location/coordinates")
    fun updateCoordinates(@Body gpsCoordinates: GpsCoordinates): Call<Void>

    @GET("/collaborator/workTime")
    fun getWorkTime(): Call<WorkTime>
}
