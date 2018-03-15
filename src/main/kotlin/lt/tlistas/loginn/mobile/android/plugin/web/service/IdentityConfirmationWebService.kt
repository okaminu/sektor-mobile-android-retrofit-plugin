package lt.tlistas.loginn.mobile.android.plugin.web.service

interface IdentityConfirmationWebService {

    @POST("/collaborator/identity-confirmation/code/request/{mobileNumber}")
    fun requestCode(@Path("mobileNumber") mobileNumber: String): Call<Void>

    @POST("/collaborator/identity-confirmation/code/confirm/{code}")
    fun confirmCode(@Path("code") confirmationCode: String): Call<String>

}