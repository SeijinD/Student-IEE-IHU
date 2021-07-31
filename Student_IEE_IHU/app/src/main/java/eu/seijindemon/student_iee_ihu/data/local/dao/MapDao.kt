package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.Map
import kotlinx.coroutines.flow.Flow

@Dao
interface MapDao {

    @Query("SELECT * FROM map_table ORDER BY id ASC")
    fun readData(): Flow<List<Map>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(maps: List<Map>)

    @Query("SELECT * FROM map_table WHERE name LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Map>>

}