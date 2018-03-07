package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkLogGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkLogRetrofitService

class WorkLogRetrofitGatewayAdapter(token: String, private val service: WorkLogRetrofitService = RetrofitServiceFactory()
        .buildService(WorkLogRetrofitService::class.java, token)) : WorkLogGateway {

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        service.logByLocation(gpsCoordinates).execute()
    }
}