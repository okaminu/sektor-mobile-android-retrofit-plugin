package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit.web.adapter

import com.nhaarman.mockitokotlin2.doReturn
import lt.boldadmin.sektor.mobile.android.api.type.entity.Project
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.Coordinates
import lt.boldadmin.sektor.mobile.android.api.type.valueobject.Location
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.adapter.ProjectRetrofitGatewayAdapter
import lt.boldadmin.sektor.mobile.android.plugin.retrofit.web.service.ProjectWebService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class ProjectRetrofitGatewayAdapterTest {

    @Mock
    private lateinit var projectWebServiceSpy: ProjectWebService

    @Mock
    private lateinit var callSpy: Call<Void>

    private lateinit var adapter: ProjectRetrofitGatewayAdapter

    @BeforeEach
    fun `Set up`() {
        adapter = ProjectRetrofitGatewayAdapter("token", projectWebServiceSpy)
    }

    @Test
    fun `Gets projects`() {
        val expectedProjects = setOf(Project("fancyId", "Skyscraper", Location(Coordinates(123.0, 321.0), 50), 5))
        doReturn(callSpy).`when`(projectWebServiceSpy).getProjects()
        doReturn(Response.success(expectedProjects)).`when`(callSpy).execute()

        val actualProjects = adapter.getProjects()

        assertEquals(expectedProjects, actualProjects)
    }
}
