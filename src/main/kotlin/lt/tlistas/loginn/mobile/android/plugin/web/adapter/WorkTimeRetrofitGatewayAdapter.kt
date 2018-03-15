package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkTimeGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.WorkTime
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.WorkTimeWebService

class WorkTimeRetrofitGatewayAdapter(
    token: String,
    private val service: WorkTimeWebService = RetrofitFactory().create(WorkTimeWebService::class.java, token)
) : WorkTimeGateway {

    override fun getWorkTime(): WorkTime = service.getWorkTime().execute().body()
}