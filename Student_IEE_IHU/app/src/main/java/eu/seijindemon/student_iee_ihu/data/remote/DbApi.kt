package eu.seijindemon.student_iee_ihu.data.remote

import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import retrofit2.Response
import retrofit2.http.GET

interface DbApi {

    @GET("/local_api.php?table=teachers")
    suspend fun getTeachers(): Response<List<Teacher>>

    @GET("/local_api.php?table=courses")
    suspend fun getCourses(): Response<List<Course>>

}