package eu.seijindemon.student_iee_ihu.refactor.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import kotlinx.coroutines.flow.Flow

@Dao
interface CommunityDao {

    @Query("SELECT * FROM community_table ORDER BY id ASC")
    fun readData(): Flow<List<Community>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(communities: List<Community>)

    @Query("SELECT * FROM community_table WHERE title_gr LIKE :searchQuery OR title_en LIKE :searchQuery OR category LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Community>>

    @Query("SELECT * FROM community_table WHERE category = 'facebook_groups' ")
    fun communityFbGroups(): Flow<List<Community>>

    @Query("SELECT * FROM community_table WHERE category = 'facebook_pages' ")
    fun communityFbPages(): Flow<List<Community>>

    @Query("SELECT * FROM community_table WHERE category = 'discord_servers' ")
    fun communityDiscordServers(): Flow<List<Community>>

    @Query("SELECT * FROM community_table WHERE category = 'other_community' ")
    fun communityOther(): Flow<List<Community>>

}