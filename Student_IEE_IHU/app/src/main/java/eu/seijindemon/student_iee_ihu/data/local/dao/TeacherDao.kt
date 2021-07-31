package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {

    @Query("SELECT * FROM teacher_table ORDER BY id ASC")
    fun readData(): Flow<List<Teacher>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(teachers: List<Teacher>)

    @Query("SELECT * FROM teacher_table WHERE name LIKE :searchQuery OR email LIKE :searchQuery OR category LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Teacher>>

}