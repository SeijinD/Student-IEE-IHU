package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM course_table ORDER BY id ASC")
    fun readData(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(course: Course)

    @Query("SELECT * FROM course_table WHERE title LIKE :searchQuery OR teachers LIKE :searchQuery OR semester LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Course>>

}