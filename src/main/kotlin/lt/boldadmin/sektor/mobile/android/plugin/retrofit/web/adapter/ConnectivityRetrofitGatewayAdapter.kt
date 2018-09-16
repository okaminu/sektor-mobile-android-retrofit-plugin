package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.ConnectivityGateway
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ConnectivityWebService

class ConnectivityRetrofitGatewayAdapter(
    private val connectivityService: ConnectivityWebService = RetrofitFactory().create(ConnectivityWebService::class.java)
): ConnectivityGateway {

    override fun isHealthy(): Boolean = connectivityService.isHealthy().execute().body()

}