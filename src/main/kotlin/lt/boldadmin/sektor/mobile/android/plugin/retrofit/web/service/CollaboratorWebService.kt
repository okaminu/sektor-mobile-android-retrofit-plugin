package lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service

import lt.boldadmin.sektor.mobile.android.api.valueobject.GpsCoordinates
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CollaboratorWebService {

    @POST("/collaborator/location/coordinates")
    fun updateLocation(@Body gpsCoordinates: GpsCoordinates): Call<Void>

}