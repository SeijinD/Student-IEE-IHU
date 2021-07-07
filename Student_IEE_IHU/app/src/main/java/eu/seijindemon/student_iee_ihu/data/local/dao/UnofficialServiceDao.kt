package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.UnofficialService
import kotlinx.coroutines.flow.Flow

@Dao
interface UnofficialServiceDao {

    @Query("SELECT * FROM unofficial_service_table ORDER BY id ASC")
    fun readData(): Flow<List<UnofficialService>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(unofficialServices: List<UnofficialService>)

    @Query("SELECT * FROM unofficial_service_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery OR creator LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>>

}