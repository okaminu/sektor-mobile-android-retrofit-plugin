package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.exception.CollaboratorNotFoundException
import lt.tlistas.loginn.mobile.android.api.exception.IncorrectConfirmationCodeException
import lt.tlistas.loginn.mobile.android.api.gateway.IdentityConfirmationGateway
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.IdentityConfirmationRetrofitService

class AuthenticationRetrofitGatewayAdapter(
    private val service: IdentityConfirmationRetrofitService = RetrofitFactory()
        .create(IdentityConfirmationRetrofitService::class.java)
) : IdentityConfirmationGateway {

    override fun requestCode(mobileNumber: String) {
        val response = service.requestCode(mobileNumber).execute()
        if (response.code() == 404)
            throw CollaboratorNotFoundException()
    }

    override fun confirmCode(confirmationCode: String): String {
        val response = service.confirmCode(confirmationCode).execute()
        if (response.code() == 404)
            throw IncorrectConfirmationCodeException()
        return response.body()
    }

}