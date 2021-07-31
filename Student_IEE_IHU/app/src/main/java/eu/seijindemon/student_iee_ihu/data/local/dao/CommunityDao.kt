package eu.seijindemon.student_iee_ihu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.data.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CommunityDao {

    @Query("SELECT * FROM community_table ORDER BY id ASC")
    fun readData(): Flow<List<Community>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(communities: List<Community>)

    @Query("SELECT * FROM community_table WHERE title LIKE :searchQuery OR category LIKE :searchQuery")
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