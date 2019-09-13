package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WorklogWebService {

    @GET("/worklog/project-name-of-started-work")
    fun getProjectNameOfStartedWork(): Call<ResponseBody>

    @GET("/worklog/has-work-started")
    fun hasWorkStarted(): Call<Boolean>
}
