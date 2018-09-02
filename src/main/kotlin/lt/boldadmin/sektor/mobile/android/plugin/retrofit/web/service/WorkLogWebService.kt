package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface WorkLogWebService {

    @GET("/worklog/collaborator/interval-ids")
    fun getIntervalIdsByCollaborator(): Call<List<String>>

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<ResponseBody>

    @GET("/worklog/has-work-started")
    fun hasWorkStarted(): Call<Boolean>

    @GET("/worklog/interval/{intervalId}/description")
    fun getDescription(@Path("intervalId") intervalId: String): Call<ResponseBody>

    @GET("/worklog/interval/{intervalId}/endpoints")
    fun getIntervalEndpoints(@Path("intervalId") intervalId: String): Call<WorkLogIntervalEndpoints>

    @GET("/worklog/interval/{intervalIds}/durations-sum")
    fun getDurationsSum(@Path("intervalIds") intervalIds: String): Call<Long>

    @POST("/worklog/log-by-location")
    fun logByLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

    @POST("/worklog/update-description/{intervalId}")
    fun updateDescription(
        @Path("intervalId") intervalId: String,
        @Body descriptionBody: RequestBody
    ): Call<Void>

}