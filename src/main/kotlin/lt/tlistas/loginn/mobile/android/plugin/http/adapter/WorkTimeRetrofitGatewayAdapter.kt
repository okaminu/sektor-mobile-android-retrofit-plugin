package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkTimeGateway
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkTimeRetrofitService

class WorkTimeRetrofitGatewayAdapter(private val service: WorkTimeRetrofitService = RetrofitServiceFactory()
        .buildService(WorkTimeRetrofitService::class.java)) : WorkTimeGateway {

    override fun getWorkTime() = service.getWorkTime().execute().body()!!
}