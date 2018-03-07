package lt.tlistas.loginn.mobile.android.plugin.http.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationRetrofitService {

    @POST("/collaborator/authentication/code/request/{mobileNumber}")
    fun requestConfirmationCode(@Path("mobileNumber") mobileNumber: String): Call<Void>

    @POST("/collaborator/authentication/code/confirm/{code}")
    fun confirmCode(@Path("code") confirmationCode: String): Call<String>

}