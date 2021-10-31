package eu.seijindemon.student_iee_ihu.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {

    @Query("SELECT * FROM teacher_table ORDER BY id ASC")
    fun readData(): Flow<List<Teacher>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(teachers: List<Teacher>)

    @Query("SELECT * FROM teacher_table WHERE name_gr LIKE :searchQuery OR name_en LIKE :searchQuery OR email LIKE :searchQuery OR category_gr LIKE :searchQuery OR category_en LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Teacher>>

}