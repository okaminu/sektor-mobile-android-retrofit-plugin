package lt.boldadmin.sektor.mobile.android.plugin.web.service

import retrofit2.Call
import retrofit2.http.GET

interface StatusWebService {

    @GET("/status")
    fun getStatus(): Call<String>

}