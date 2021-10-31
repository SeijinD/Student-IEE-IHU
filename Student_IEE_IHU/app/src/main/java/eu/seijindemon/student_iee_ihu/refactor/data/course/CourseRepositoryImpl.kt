package eu.seijindemon.student_iee_ihu.refactor.data.course

import eu.seijindemon.student_iee_ihu.refactor.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val dataSource: CourseDataSource
) : CourseRepository {

    override fun readData(): Flow<List<Course>> {
        return dataSource.readData()
    }

    override suspend fun insertData(courses: List<Course>) {
        dataSource.insertData(courses)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Course>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getCourses(): Response<List<Course>> {
        return dataSource.getCourses()
    }

}