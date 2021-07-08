package eu.seijindemon.student_iee_ihu.data.repository

import eu.seijindemon.student_iee_ihu.data.local.dao.MapDao
import eu.seijindemon.student_iee_ihu.data.model.Map
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MapRepository(private val mapDao: MapDao) {

    fun readData(): Flow<List<Map>> {
        return mapDao.readData()
    }

    suspend fun insertData(maps: List<Map>) {
        mapDao.insertData(maps)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Map>> {
        return mapDao.searchDatabase(searchQuery)
    }

    suspend fun getMaps(): Response<List<Map>> {
        return RetrofitInstance.dbApi.getMaps()
    }

}