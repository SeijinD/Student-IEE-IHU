package eu.seijindemon.student_iee_ihu.refactor.data.community

import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: CommunityDataSource
) : CommunityRepository {

    override fun readData(): Flow<List<Community>> {
        return dataSource.readData()
    }

    override suspend fun insertData(communities: List<Community>) {
        dataSource.insertData(communities)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Community>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override fun communityFbGroups(): Flow<List<Community>> {
        return dataSource.communityFbGroups()
    }

    override fun communityFbPages(): Flow<List<Community>> {
        return dataSource.communityFbPages()
    }

    override fun communityDiscordServers(): Flow<List<Community>> {
        return dataSource.communityDiscordServers()
    }

    override fun communityOther(): Flow<List<Community>> {
        return dataSource.communityOther()
    }

    override suspend fun getCommunities(): Response<List<Community>> {
        return dataSource.getCommunities()
    }

}