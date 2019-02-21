package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorklogGateway
import lt.boldadmin.sektor.mobile.android.api.type.entity.WorkLogIntervalEndpoints
import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorklogWebService

class WorklogRetrofitGatewayAdapter(
    token: String,
    private val webService: WorklogWebService = RetrofitFactory().create(WorklogWebService::class.java, token)
): WorklogGateway {

    override fun getIntervalIdsByCollaborator(): Collection<String> =
        webService.getIntervalIdsByCollaborator().execute().body()

    override fun getProjectNameOfStartedWork(): String =
        webService.getProjectNameOfStartedWork().execute().body().string()

    override fun hasWorkStarted(): Boolean = webService.hasWorkStarted().execute().body()

    override fun getIntervalEndpoints(intervalId: String): WorkLogIntervalEndpoints =
        webService.getIntervalEndpoints(intervalId).execute().body()

    override fun getDurationsSum(intervalIds: Collection<String>): Long =
        webService.getDurationsSum(intervalIds.joinToString(",")).execute().body()

    override fun logByLocation(gpsCoordinates: GpsCoordinates) {
        webService.logByLocation(gpsCoordinates).execute()
    }
}