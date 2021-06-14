package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.CommunityDao
import eu.seijindemon.student_iee_ihu.data.model.Community
import kotlinx.coroutines.flow.Flow

class CommunityRepository(private val communityDao: CommunityDao) {

    fun readData(): Flow<List<Community>> {
        return communityDao.readData()
    }

    suspend fun insertData(community: Community) {
        communityDao.insertData(community)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Community>> {
        return communityDao.searchDatabase(searchQuery)
    }

    fun communityFbGroups(): Flow<List<Community>> {
        return communityDao.communityFbGroups()
    }

    fun communityFbPages(): Flow<List<Community>> {
        return communityDao.communityFbPages()
    }

    fun communityDiscordServers(): Flow<List<Community>> {
        return communityDao.communityDiscordServers()
    }

    fun communityOther(): Flow<List<Community>> {
        return communityDao.communityOther()
    }

}