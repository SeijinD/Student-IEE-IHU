package eu.seijindemon.student_iee_ihu.data.repository

import eu.seijindemon.student_iee_ihu.data.local.dao.CourseDao
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CourseRepository(private val courseDao: CourseDao) {

    fun readData(): Flow<List<Course>> {
        return courseDao.readData()
    }

    suspend fun insertData(courses: List<Course>) {
        courseDao.insertData(courses)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Course>> {
        return courseDao.searchDatabase(searchQuery)
    }

    suspend fun getCourses(): Response<List<Course>> {
        return RetrofitInstance.dbApi.getCourses()
    }

}