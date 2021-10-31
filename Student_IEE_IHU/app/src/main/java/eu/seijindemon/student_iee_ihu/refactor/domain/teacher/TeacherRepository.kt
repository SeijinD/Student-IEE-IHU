package eu.seijindemon.student_iee_ihu.refactor.domain.teacher

import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TeacherRepository {

    fun readData(): Flow<List<Teacher>>

    suspend fun insertData(teachers: List<Teacher>)

    fun searchDatabase(searchQuery: String): Flow<List<Teacher>>

    suspend fun getTeachers(): Response<List<Teacher>>

}