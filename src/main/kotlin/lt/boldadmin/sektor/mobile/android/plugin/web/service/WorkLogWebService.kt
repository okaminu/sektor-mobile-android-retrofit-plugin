package lt.boldadmin.sektor.mobile.android.plugin.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkLogWebService {

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<String>

}