package eu.seijindemon.student_iee_ihu.refactor.framework.teacher

import eu.seijindemon.student_iee_ihu.refactor.data.teacher.TeacherDataSource
import eu.seijindemon.student_iee_ihu.refactor.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.refactor.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class TeacherDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : TeacherDataSource {

    override fun readData(): Flow<List<Teacher>> {
        return appDb.teacherDao().readData()
    }

    override suspend fun insertData(teachers: List<Teacher>) {
        appDb.teacherDao().insertData(teachers)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Teacher>> {
        return appDb.teacherDao().searchDatabase(searchQuery)
    }

    override suspend fun getTeachers(): Response<List<Teacher>> {
        return api.getTeachers()
    }

}