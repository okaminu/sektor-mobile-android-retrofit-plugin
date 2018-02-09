package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkLogGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.GpsCoordinates
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkLogRetrofitService

class WorkLogRetrofitGatewayAdapter(private val service: WorkLogRetrofitService = RetrofitServiceFactory()
        .buildService(WorkLogRetrofitService::class.java)) : WorkLogGateway {

    override fun logWorkByLocation(gpsCoordinates: GpsCoordinates) {
        service.logWorkByLocation(gpsCoordinates).execute()
    }
}