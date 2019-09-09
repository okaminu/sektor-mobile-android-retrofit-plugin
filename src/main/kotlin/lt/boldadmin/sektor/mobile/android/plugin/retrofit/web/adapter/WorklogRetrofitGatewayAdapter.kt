package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorklogGateway
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorklogWebService

class WorklogRetrofitGatewayAdapter(
    token: String,
    private val webService: WorklogWebService = RetrofitFactory().create(WorklogWebService::class.java, token)
): WorklogGateway {

    override fun getProjectNameOfStartedWork(): String =
        webService.getProjectNameOfStartedWork().execute().body().string()

    override fun hasWorkStarted(): Boolean = webService.hasWorkStarted().execute().body()

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webService.logByLocation(gpsCoordinates).execute()
    }
}
