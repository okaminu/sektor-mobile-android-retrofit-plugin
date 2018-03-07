package lt.tlistas.loginn.mobile.android.plugin.http.adapter

import lt.tlistas.loginn.mobile.android.api.gateway.WorkTimeGateway
import lt.tlistas.loginn.mobile.android.plugin.http.RetrofitServiceFactory
import lt.tlistas.loginn.mobile.android.plugin.http.service.WorkTimeRetrofitService

class WorkTimeRetrofitGatewayAdapter(token: String,
                                     private val service: WorkTimeRetrofitService = RetrofitServiceFactory()
                                             .buildService(WorkTimeRetrofitService::class.java, token)
) : WorkTimeGateway {

    override fun getWorkTime() = service.getWorkTime().execute().body()!!
}