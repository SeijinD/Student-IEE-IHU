package eu.seijindemon.student_iee_ihu.data.remote

import eu.seijindemon.student_iee_ihu.data.model.*
import eu.seijindemon.student_iee_ihu.data.model.Map
import retrofit2.Response
import retrofit2.http.GET

interface DbApi {

    @GET("/local_api.php?table=teachers")
    suspend fun getTeachers(): Response<List<Teacher>>

    @GET("/local_api.php?table=courses")
    suspend fun getCourses(): Response<List<Course>>

    @GET("/local_api.php?table=communities")
    suspend fun getCommunities(): Response<List<Community>>

    @GET("/local_api.php?table=maps")
    suspend fun getMaps(): Response<List<Map>>

    @GET("/local_api.php?table=offers")
    suspend fun getOffers(): Response<List<Offer>>

    @GET("/local_api.php?table=official_services")
    suspend fun getOfficialServices(): Response<List<OfficialService>>

    @GET("/local_api.php?table=unofficial_services")
    suspend fun getUnofficialServices(): Response<List<UnofficialService>>

    @GET("/local_api.php?table=useful_websites")
    suspend fun getUsefulWebsites(): Response<List<UsefulWebsite>>

}