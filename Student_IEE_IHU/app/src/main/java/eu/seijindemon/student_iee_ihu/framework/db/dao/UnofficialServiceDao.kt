package eu.seijindemon.student_iee_ihu.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow

@Dao
interface UnofficialServiceDao {

    @Query("SELECT * FROM unofficial_service_table ORDER BY id ASC")
    fun readData(): Flow<List<UnofficialService>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(unofficialServices: List<UnofficialService>)

    @Query("SELECT * FROM unofficial_service_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR description_gr LIKE :searchQuery OR description_en LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery OR creator_gr LIKE :searchQuery OR creator_en LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>>

}