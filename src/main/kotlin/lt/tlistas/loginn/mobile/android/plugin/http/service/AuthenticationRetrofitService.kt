package lt.tlistas.loginn.mobile.android.plugin.http.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationRetrofitService {

    @POST("/mobile/register/mobileNumber/{mobileNumber}")
    fun registerMobileNumber(@Path("mobileNumber") mobileNumber: String): Call<Void>

    @POST("/mobile/confirm/code/{confirmationCode}")
    fun confirmCode(@Path("confirmationCode") confirmationCode: String): Call<String>

}