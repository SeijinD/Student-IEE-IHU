package eu.seijindemon.student_iee_ihu.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import kotlinx.coroutines.flow.Flow

@Dao
interface MapDao {

    @Query("SELECT * FROM map_table ORDER BY id ASC")
    fun readData(): Flow<List<Map>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(maps: List<Map>)

    @Query("SELECT * FROM map_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR description_gr LIKE :searchQuery OR description_en LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Map>>

}