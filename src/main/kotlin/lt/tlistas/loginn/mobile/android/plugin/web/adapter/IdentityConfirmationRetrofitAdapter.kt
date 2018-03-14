package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.exception.CollaboratorNotFoundException
import lt.tlistas.loginn.mobile.android.api.exception.IncorrectConfirmationCodeException
import lt.tlistas.loginn.mobile.android.api.gateway.IdentityConfirmationGateway
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.IdentityConfirmationWebService

class IdentityConfirmationRetrofitAdapter(
    private val webClient: IdentityConfirmationWebService = RetrofitFactory()
        .create(IdentityConfirmationWebService::class.java)
) : IdentityConfirmationGateway {

    override fun requestCode(mobileNumber: String) {
        val response = webClient.requestCode(mobileNumber).execute()
        if (response.code() == 404)
            throw CollaboratorNotFoundException()
    }

    override fun confirmCode(confirmationCode: String): String {
        val response = webClient.confirmCode(confirmationCode).execute()
        if (response.code() == 401)
            throw IncorrectConfirmationCodeException()
        return response.body()
    }

}