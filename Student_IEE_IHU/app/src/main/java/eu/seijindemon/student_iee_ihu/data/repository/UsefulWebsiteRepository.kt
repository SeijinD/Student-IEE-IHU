package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.UsefulWebsiteDao
import eu.seijindemon.student_iee_ihu.data.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow

class UsefulWebsiteRepository(private val usefulWebsiteDao: UsefulWebsiteDao) {

    fun readData(): Flow<List<UsefulWebsite>> {
        return usefulWebsiteDao.readData()
    }

    suspend fun insertData(usefulWebsites: List<UsefulWebsite>) {
        usefulWebsiteDao.insertData(usefulWebsites)
    }

    fun searchDatabase(searchQuery: String): Flow<List<UsefulWebsite>> {
        return usefulWebsiteDao.searchDatabase(searchQuery)
    }

}