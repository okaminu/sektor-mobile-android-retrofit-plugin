package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.WorkTimeGateway
import lt.boldadmin.sektor.mobile.android.api.valueobject.WorkTime
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.WorkTimeWebService

class WorkTimeRetrofitGatewayAdapter(
    token: String,
    private val webService: WorkTimeWebService = RetrofitFactory().create(WorkTimeWebService::class.java, token)
) : WorkTimeGateway {

    override fun getWorkTime(): WorkTime = webService.getWorkTime().execute().body()
}