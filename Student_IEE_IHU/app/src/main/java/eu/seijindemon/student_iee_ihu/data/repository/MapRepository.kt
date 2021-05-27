package eu.seijindemon.student_iee_ihu.data.repository

import eu.seijindemon.student_iee_ihu.data.local.dao.MapDao
import eu.seijindemon.student_iee_ihu.data.model.Map
import kotlinx.coroutines.flow.Flow

class MapRepository(private val mapDao: MapDao) {

    fun readData(): Flow<List<Map>> {
        return mapDao.readData()
    }

    suspend fun insertData(map: Map) {
        mapDao.insertData(map)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Map>> {
        return mapDao.searchDatabase(searchQuery)
    }

}