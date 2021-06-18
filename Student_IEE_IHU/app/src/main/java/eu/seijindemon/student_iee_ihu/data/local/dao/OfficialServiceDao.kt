package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.OfficialService
import kotlinx.coroutines.flow.Flow

@Dao
interface OfficialServiceDao {

    @Query("SELECT * FROM official_service_table ORDER BY id ASC")
    fun readData(): Flow<List<OfficialService>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(officialService: OfficialService)

    @Query("SELECT * FROM offer_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<OfficialService>>

}