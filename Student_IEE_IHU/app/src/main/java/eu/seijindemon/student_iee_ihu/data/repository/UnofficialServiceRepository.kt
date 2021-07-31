package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.UnofficialServiceDao
import eu.seijindemon.student_iee_ihu.data.model.UnofficialService
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UnofficialServiceRepository(private val unofficialServiceDao: UnofficialServiceDao) {

    fun readData(): Flow<List<UnofficialService>> {
        return unofficialServiceDao.readData()
    }

    suspend fun insertData(unofficialServices: List<UnofficialService>) {
        unofficialServiceDao.insertData(unofficialServices)
    }

    fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>> {
        return unofficialServiceDao.searchDatabase(searchQuery)
    }

    suspend fun getUnofficialServices(): Response<List<UnofficialService>> {
        return RetrofitInstance.dbApi.getUnofficialServices()
    }

}