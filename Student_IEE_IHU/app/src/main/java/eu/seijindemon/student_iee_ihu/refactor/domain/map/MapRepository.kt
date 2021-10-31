package eu.seijindemon.student_iee_ihu.refactor.domain.map

import eu.seijindemon.student_iee_ihu.refactor.framework.map.model.Map
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MapRepository {

    fun readData(): Flow<List<Map>>

    suspend fun insertData(maps: List<Map>)

    fun searchDatabase(searchQuery: String): Flow<List<Map>>

    suspend fun getMaps(): Response<List<Map>>

}