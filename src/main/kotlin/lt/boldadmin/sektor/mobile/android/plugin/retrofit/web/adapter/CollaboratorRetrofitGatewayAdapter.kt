package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.CollaboratorGateway
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.CollaboratorWebService

class CollaboratorRetrofitGatewayAdapter(
    token: String,
    private val webService: CollaboratorWebService = RetrofitFactory().create(CollaboratorWebService::class.java, token)
): CollaboratorGateway {

    override fun updateLocation(gpsCoordinates: GpsCoordinates) {
        webService.updateLocation(gpsCoordinates).execute()
    }
}