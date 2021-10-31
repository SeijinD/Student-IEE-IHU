package eu.seijindemon.student_iee_ihu.data.teacher

import eu.seijindemon.student_iee_ihu.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val dataSource: TeacherDataSource
) : TeacherRepository {

    override fun readData(): Flow<List<Teacher>> {
        return dataSource.readData()
    }

    override suspend fun insertData(teachers: List<Teacher>) {
        dataSource.insertData(teachers)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Teacher>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getTeachers(): Response<List<Teacher>> {
        return dataSource.getTeachers()
    }

}