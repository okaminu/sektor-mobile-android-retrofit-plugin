package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import retrofit2.Call
import retrofit2.http.GET

interface StatusWebService {

    @GET("/healthy")
    fun isHealthy(): Call<Boolean>

}