package lt.boldadmin.sektor.mobile.android.plugin.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import retrofit2.Call
import retrofit2.http.*

interface WorkLogWebService {

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<String>

    @GET("/worklog/has-work-started")
    fun hasWorkStarted(): Call<Boolean>

    @POST("/worklog/update-description/{intervalId}")
    fun updateDescription(
        @Path("intervalId") intervalId: String,
        @Body description: String
    ): Call<Void>

}