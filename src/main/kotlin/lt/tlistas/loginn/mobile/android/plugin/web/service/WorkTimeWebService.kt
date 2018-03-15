package lt.tlistas.loginn.mobile.android.plugin.web.service

import lt.tlistas.loginn.mobile.android.api.type.valueobject.WorkTime
import retrofit2.Call
import retrofit2.http.GET

interface WorkTimeWebService {

    @GET("/collaborator/workTime")
    fun getWorkTime(): Call<WorkTime>

}