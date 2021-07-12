package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.UsefulWebsiteDao
import eu.seijindemon.student_iee_ihu.data.model.UsefulWebsite
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

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

    suspend fun getUsefulWebsites(): Response<List<UsefulWebsite>> {
        return RetrofitInstance.dbApi.getUsefulWebsites()
    }

}