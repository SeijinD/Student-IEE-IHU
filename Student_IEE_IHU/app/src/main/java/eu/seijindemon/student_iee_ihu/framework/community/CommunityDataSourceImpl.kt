package eu.seijindemon.student_iee_ihu.framework.community

import eu.seijindemon.student_iee_ihu.data.community.CommunityDataSource
import eu.seijindemon.student_iee_ihu.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.framework.community.model.Community
import eu.seijindemon.student_iee_ihu.framework.db.AppDb
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CommunityDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : CommunityDataSource {

    override fun readData(): Flow<List<Community>> {
        return appDb.communityDao().readData()
    }

    override suspend fun insertData(communities: List<Community>) {
        appDb.communityDao().insertData(communities)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Community>> {
        return appDb.communityDao().searchDatabase(searchQuery)
    }

    override fun communityFbGroups(): Flow<List<Community>> {
        return appDb.communityDao().communityFbGroups()
    }

    override fun communityFbPages(): Flow<List<Community>> {
        return appDb.communityDao().communityFbPages()
    }

    override fun communityDiscordServers(): Flow<List<Community>> {
        return appDb.communityDao().communityDiscordServers()
    }

    override fun communityOther(): Flow<List<Community>> {
        return appDb.communityDao().communityOther()
    }

    override suspend fun getCommunities(): Response<List<Community>> {
        return api.getCommunities()
    }

}