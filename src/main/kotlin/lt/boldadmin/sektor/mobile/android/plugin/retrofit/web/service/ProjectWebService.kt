package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.type.entity.Project
import retrofit2.Call
import retrofit2.http.GET

interface ProjectWebService {

    @GET("/projects")
    fun getProjects(): Call<Collection<Project>>
}
