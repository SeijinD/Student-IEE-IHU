package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.CourseDao
import eu.seijindemon.student_iee_ihu.data.model.Course
import kotlinx.coroutines.flow.Flow

class CourseRepository(private val courseDao: CourseDao) {

    fun readData(): Flow<List<Course>> {
        return courseDao.readData()
    }

    suspend fun insertData(course: Course) {
        courseDao.insertData(course)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Course>> {
        return courseDao.searchDatabase(searchQuery)
    }

}