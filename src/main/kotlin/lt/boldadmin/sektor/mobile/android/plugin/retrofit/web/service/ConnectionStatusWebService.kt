package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import retrofit2.Call
import retrofit2.http.GET

interface ConnectionStatusWebService {

    @GET("/is-healthy")
    fun isHealthy(): Call<Boolean>

}