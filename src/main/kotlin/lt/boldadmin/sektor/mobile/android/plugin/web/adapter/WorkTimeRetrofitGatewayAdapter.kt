package lt.boldadmin.sektor.mobile.android.plugin.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorkTimeGateway
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.WorkTime
import lt.boldadmin.sektor.mobile.android.plugin.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.web.service.WorkTimeWebService

class WorkTimeRetrofitGatewayAdapter(
    token: String,
    private val service: WorkTimeWebService = RetrofitFactory().create(
            WorkTimeWebService::class.java, token)
) : WorkTimeGateway {

    override fun getWorkTime(): WorkTime = service.getWorkTime().execute().body()
}