package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.CollaboratorGateway
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.Coordinates
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.DayMinuteInterval
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.CollaboratorWebService
import java.util.*

class CollaboratorRetrofitGatewayAdapter(
    token: String,
    private val webService: CollaboratorWebService = RetrofitFactory().create(CollaboratorWebService::class.java, token)
): CollaboratorGateway {

    override fun updateCoordinates(coordinates: Coordinates) {
        webService.updateCoordinates(coordinates).execute()
    }

    override fun getWorkWeek(): SortedSet<DayMinuteInterval> = webService.getWorkWeek().execute().body()!!
}
