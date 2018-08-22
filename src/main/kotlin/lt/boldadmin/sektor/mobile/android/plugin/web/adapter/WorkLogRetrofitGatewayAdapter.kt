package lt.boldadmin.sektor.mobile.android.plugin.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorkLogGateway
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.web.service.WorkLogWebService
import okhttp3.MediaType
import okhttp3.RequestBody

class WorkLogRetrofitGatewayAdapter(
    token: String, private val webClient: WorkLogWebService = RetrofitFactory().create(WorkLogWebService::class.java, token)
) : WorkLogGateway {

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webClient.logByLocation(gpsCoordinates).execute()
    }

    override fun getProjectNameOfStartedWork(): String = webClient.getProjectNameOfStartedWork()
        .execute()
        .body()
        .string()

    override fun hasWorkStarted(): Boolean = webClient.hasWorkStarted().execute().body()

    override fun updateDescription(intervalId: String, description: String) {
        val descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description)
        webClient.updateDescription(intervalId, descriptionBody).execute()
    }

}