package eu.seijindemon.student_iee_ihu.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferDao {

    @Query("SELECT * FROM offer_table ORDER BY id ASC")
    fun readData(): Flow<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(offers: List<Offer>)

    @Query("SELECT * FROM offer_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR category LIKE :searchQuery OR link LIKE :searchQuery OR description_gr LIKE :searchQuery OR description_en LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Offer>>

}