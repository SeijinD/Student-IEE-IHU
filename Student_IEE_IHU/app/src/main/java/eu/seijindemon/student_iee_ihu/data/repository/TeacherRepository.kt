package eu.seijindemon.student_iee_ihu.data.repository

import eu.seijindemon.student_iee_ihu.data.local.dao.TeacherDao
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import kotlinx.coroutines.flow.Flow

class TeacherRepository(private val teacherDao: TeacherDao) {

    fun readData(): Flow<List<Teacher>> {
        return teacherDao.readData()
    }

    suspend fun insertData(teacher: Teacher) {
        teacherDao.insertData(teacher)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Teacher>> {
        return teacherDao.searchDatabase(searchQuery)
    }

}