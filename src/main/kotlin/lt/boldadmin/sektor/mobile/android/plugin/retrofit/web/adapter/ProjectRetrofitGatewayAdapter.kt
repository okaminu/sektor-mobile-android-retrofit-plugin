package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter

import lt.boldadmin.sektor.mobile.android.api.gateway.ProjectGateway
import lt.boldadmin.sektor.mobile.android.api.type.entity.Project
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.factory.RetrofitFactory
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ProjectWebService

class ProjectRetrofitGatewayAdapter(
    token: String,
    private val webService: ProjectWebService = RetrofitFactory().create(ProjectWebService::class.java, token)
): ProjectGateway {

    override fun getProjects(): Collection<Project> = webService.getProjects().execute().body()!!
}
