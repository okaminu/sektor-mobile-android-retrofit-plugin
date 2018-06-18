package lt.boldadmin.sektor.mobile.android.plugin.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorkLogGateway
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.web.service.WorkLogWebService

class WorkLogRetrofitGatewayAdapter(token: String, private val webClient: WorkLogWebService = RetrofitFactory()
        .create(WorkLogWebService::class.java, token)) : WorkLogGateway {

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webClient.logByLocation(gpsCoordinates).execute()
    }
}