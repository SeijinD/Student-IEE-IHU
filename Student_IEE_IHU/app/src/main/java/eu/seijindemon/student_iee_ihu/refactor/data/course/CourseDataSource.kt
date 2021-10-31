package eu.seijindemon.student_iee_ihu.refactor.data.course

import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CourseDataSource{

    fun readData(): Flow<List<Course>>

    suspend fun insertData(courses: List<Course>)

    fun searchDatabase(searchQuery: String): Flow<List<Course>>

    suspend fun getCourses(): Response<List<Course>>

}