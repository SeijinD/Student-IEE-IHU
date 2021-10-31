package eu.seijindemon.student_iee_ihu.refactor.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow

@Dao
interface OfficialServiceDao {

    @Query("SELECT * FROM official_service_table ORDER BY id ASC")
    fun readData(): Flow<List<OfficialService>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(officialServices: List<OfficialService>)

    @Query("SELECT * FROM official_service_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR description_gr LIKE :searchQuery OR description_en LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<OfficialService>>

}