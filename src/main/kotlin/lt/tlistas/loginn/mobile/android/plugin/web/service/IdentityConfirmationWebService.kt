package lt.tlistas.loginn.mobile.android.plugin.web.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface IdentityConfirmationWebService {

    @POST("/collaborator/identity-confirmation/code/request/{mobileNumber}")
    fun requestCode(@Path("mobileNumber") mobileNumber: String): Call<Void>

    @POST("/collaborator/identity-confirmation/code/confirm/{code}")
    fun confirmCode(@Path("code") confirmationCode: String): Call<String>

}