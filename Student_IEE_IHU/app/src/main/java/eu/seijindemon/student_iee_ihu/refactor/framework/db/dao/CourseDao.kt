package eu.seijindemon.student_iee_ihu.refactor.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM course_table ORDER BY id ASC")
    fun readData(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(courses: List<Course>)

    @Query("SELECT * FROM course_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR teachers_gr LIKE :searchQuery OR teachers_en LIKE :searchQuery OR semester LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Course>>

}