package eu.seijindemon.student_iee_ihu.refactor.framework.course

import eu.seijindemon.student_iee_ihu.refactor.data.course.CourseDataSource
import eu.seijindemon.student_iee_ihu.refactor.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import eu.seijindemon.student_iee_ihu.refactor.framework.db.AppDb
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CourseDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : CourseDataSource {

    override fun readData(): Flow<List<Course>> {
        return appDb.courseDao().readData()
    }

    override suspend fun insertData(courses: List<Course>) {
        appDb.courseDao().insertData(courses)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Course>> {
        return appDb.courseDao().searchDatabase(searchQuery)
    }

    override suspend fun getCourses(): Response<List<Course>> {
        return api.getCourses()
    }

}