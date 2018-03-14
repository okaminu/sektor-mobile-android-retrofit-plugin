package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkLogGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.WorkLogWebService

class WorkLogRetrofitGatewayAdapter(token: String, private val webClient: WorkLogWebService = RetrofitFactory()
        .create(WorkLogWebService::class.java, token)) : WorkLogGateway {

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webClient.logByLocation(gpsCoordinates).execute()
    }
}