package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorkLogGateway
import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkLogWebService
import okhttp3.MediaType
import okhttp3.RequestBody

class WorkLogRetrofitGatewayAdapter(
    token: String,
    private val webService: WorkLogWebService = RetrofitFactory().create(WorkLogWebService::class.java, token)
): WorkLogGateway {

    override fun getIntervalIdsByCollaborator(): Collection<String> =
        webService.getIntervalIdsByCollaborator().execute().body()

    override fun getProjectNameOfStartedWork(): String =
        webService.getProjectNameOfStartedWork().execute().body().string()

    override fun hasWorkStarted(): Boolean = webService.hasWorkStarted().execute().body()

    override fun getIntervalEndpoints(intervalId: String): WorkLogIntervalEndpoints =
        webService.getIntervalEndpoints(intervalId).execute().body()

    override fun getDescription(intervalId: String): String =
        webService.getDescription(intervalId).execute().body().string()

    override fun getDurationsSum(intervalIds: Collection<String>): Long =
        webService.getDurationsSum(intervalIds.joinToString(",")).execute().body()

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webService.logByLocation(gpsCoordinates).execute()
    }

    override fun updateDescription(intervalId: String, description: String) {
        val descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description)
        webService.updateDescription(intervalId, descriptionBody).execute()
    }

}