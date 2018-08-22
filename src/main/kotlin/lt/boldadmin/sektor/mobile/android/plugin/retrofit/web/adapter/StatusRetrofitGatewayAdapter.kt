package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.StatusGateway
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.StatusWebService

class StatusRetrofitGatewayAdapter(
    private val webClient: StatusWebService = RetrofitFactory().create(
        StatusWebService::class.java)
): StatusGateway {

    override fun status(): String = webClient.getStatus().execute().body()

}