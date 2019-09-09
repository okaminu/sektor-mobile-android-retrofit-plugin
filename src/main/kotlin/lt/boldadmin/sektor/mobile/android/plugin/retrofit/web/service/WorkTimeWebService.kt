package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.WorkTime
import retrofit2.Call
import retrofit2.http.GET

interface WorkTimeWebService {

    @GET("/collaborator/workTime")
    fun getWorkTime(): Call<WorkTime>

}
