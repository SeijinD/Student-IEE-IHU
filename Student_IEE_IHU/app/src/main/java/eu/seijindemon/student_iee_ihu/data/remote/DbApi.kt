package eu.seijindemon.student_iee_ihu.data.remote

import eu.seijindemon.student_iee_ihu.data.model.Teacher
import retrofit2.Response
import retrofit2.http.GET

interface DbApi {

    @GET("/local_api.php")
    suspend fun getTeachers(): Response<List<Teacher>>



}