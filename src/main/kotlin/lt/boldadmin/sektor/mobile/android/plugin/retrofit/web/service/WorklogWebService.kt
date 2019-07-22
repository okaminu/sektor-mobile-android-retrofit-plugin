package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorklogWebService {

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<ResponseBody>

    @GET("/worklog/has-work-started")
    fun hasWorkStarted(): Call<Boolean>

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

}
