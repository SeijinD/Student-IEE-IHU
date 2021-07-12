package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.OfficialServiceDao
import eu.seijindemon.student_iee_ihu.data.model.OfficialService
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class OfficialServiceRepository(private val officialServiceDao: OfficialServiceDao) {

    fun readData(): Flow<List<OfficialService>> {
        return officialServiceDao.readData()
    }

    suspend fun insertData(officialServices: List<OfficialService>) {
        officialServiceDao.insertData(officialServices)
    }

    fun searchDatabase(searchQuery: String): Flow<List<OfficialService>> {
        return officialServiceDao.searchDatabase(searchQuery)
    }

    suspend fun getOfficialServices(): Response<List<OfficialService>> {
        return RetrofitInstance.dbApi.getOfficialServices()
    }

}