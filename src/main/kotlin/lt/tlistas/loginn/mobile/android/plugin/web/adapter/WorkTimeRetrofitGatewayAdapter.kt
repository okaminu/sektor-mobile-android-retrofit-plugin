package lt.tlistas.loginn.mobile.android.plugin.web.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkTimeGateway
import lt.tlistas.loginn.mobile.android.api.type.valueobject.WorkTime
import lt.tlistas.loginn.mobile.android.plugin.web.factory.RetrofitFactory
import lt.tlistas.loginn.mobile.android.plugin.web.service.WorkTimeRetrofitService

class WorkTimeRetrofitGatewayAdapter(
    token: String,
    private val service: WorkTimeRetrofitService = RetrofitFactory().create(WorkTimeRetrofitService::class.java, token)
) : WorkTimeGateway {

    override fun getWorkTime() = service.getWorkTime().execute().body() as WorkTime
}