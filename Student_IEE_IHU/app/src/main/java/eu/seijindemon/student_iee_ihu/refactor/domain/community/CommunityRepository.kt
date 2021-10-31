package eu.seijindemon.student_iee_ihu.refactor.domain.community

import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CommunityRepository {

    fun readData(): Flow<List<Community>>

    suspend fun insertData(communities: List<Community>)

    fun searchDatabase(searchQuery: String): Flow<List<Community>>

    fun communityFbGroups(): Flow<List<Community>>

    fun communityFbPages(): Flow<List<Community>>

    fun communityDiscordServers(): Flow<List<Community>>

    fun communityOther(): Flow<List<Community>>

    suspend fun getCommunities(): Response<List<Community>>

}