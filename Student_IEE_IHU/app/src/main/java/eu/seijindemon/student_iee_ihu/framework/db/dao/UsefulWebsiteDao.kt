package eu.seijindemon.student_iee_ihu.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow

@Dao
interface UsefulWebsiteDao {

    @Query("SELECT * FROM useful_website_table ORDER BY id ASC")
    fun readData(): Flow<List<UsefulWebsite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(usefulWebsites: List<UsefulWebsite>)

    @Query("SELECT * FROM useful_website_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery OR description_gr LIKE :searchQuery OR description_en LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<UsefulWebsite>>

}