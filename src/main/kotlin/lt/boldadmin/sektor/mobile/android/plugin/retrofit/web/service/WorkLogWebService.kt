package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface WorkLogWebService {

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<ResponseBody>

    @GET("/worklog/has-work-started")
    fun hasWorkStarted(): Call<Boolean>

    @GET("/worklog/collaborator/interval-ids")
    fun getIntervalIdsByCollaborator(): Call<List<String>>

    @POST("/worklog/update-description/{intervalId}")
    fun updateDescription(
        @Path("intervalId") intervalId: String,
        @Body descriptionBody: RequestBody
    ): Call<Void>
}