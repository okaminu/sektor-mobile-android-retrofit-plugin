package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkLogGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.WorkLogRetrofitService

class WorkLogRetrofitGatewayAdapter(token: String, private val service: WorkLogRetrofitService = RetrofitFactory()
        .create(WorkLogRetrofitService::class.java, token)) : WorkLogGateway {

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        service.logByLocation(gpsCoordinates).execute()
    }
}