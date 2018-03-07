package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.backend.exception.AuthenticationException
import lt.tlistas.loginn.backend.exception.CollaboratorNotFoundException
import lt.tlistas.loginn.mobile.android.api.gateway.AuthenticationGateway
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.AuthenticationRetrofitService

class AuthenticationRetrofitGatewayAdapter(private val service: AuthenticationRetrofitService = RetrofitServiceFactory()
        .buildService(AuthenticationRetrofitService::class.java)) : AuthenticationGateway {

    override fun requestConfirmationCode(mobileNumber: String) {
        val response = service.registerMobileNumber(mobileNumber).execute()
        if (response.code() == 404)
            throw CollaboratorNotFoundException()
    }

    override fun confirmCode(confirmationCode: String): String {
        val response = service.confirmCode(confirmationCode).execute()
        if (response.code() == 404)
            throw AuthenticationException()
        return response.body()
    }

}