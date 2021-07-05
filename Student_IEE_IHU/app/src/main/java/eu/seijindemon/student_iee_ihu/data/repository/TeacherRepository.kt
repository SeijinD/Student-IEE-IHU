package eu.seijindemon.student_iee_ihu.data.repository

import eu.seijindemon.student_iee_ihu.data.local.dao.TeacherDao
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class TeacherRepository(private val teacherDao: TeacherDao) {

    fun readData(): Flow<List<Teacher>> {
        return teacherDao.readData()
    }

    suspend fun insertData(teachers: List<Teacher>) {
        teacherDao.insertData(teachers)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Teacher>> {
        return teacherDao.searchDatabase(searchQuery)
    }

    suspend fun getTeachers(): Response<List<Teacher>> {
        return RetrofitInstance.dbApi.getTeachers()
    }

}