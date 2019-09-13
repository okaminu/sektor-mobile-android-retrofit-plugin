package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.ConnectionStatusGateway
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ConnectionStatusWebService

class ConnectionStatusRetrofitGatewayAdapter(
    private val connectionStatusService: ConnectionStatusWebService =
        RetrofitFactory().create(ConnectionStatusWebService::class.java)
): ConnectionStatusGateway {

    override fun isHealthy(): Boolean = connectionStatusService.isHealthy().execute().body()!!

}
