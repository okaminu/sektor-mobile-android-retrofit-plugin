package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.AuthenticationGateway
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.AuthenticationRetrofitService

class AuthenticationRetrofitGatewayAdapter(private val service: AuthenticationRetrofitService = RetrofitServiceFactory()
        .buildService(AuthenticationRetrofitService::class.java)) : AuthenticationGateway {

    override fun requestConfirmationCode(mobileNumber: String) {
        service.registerMobileNumber(mobileNumber).execute()
    }

    override fun confirmCode(confirmationCode: String): String {
        return service.confirmCode(confirmationCode).execute().body()
    }

}