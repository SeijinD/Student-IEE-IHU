package eu.seijindemon.student_iee_ihu.data.map

import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val dataSource: MapDataSource
) : MapRepository {

    override fun readData(): Flow<List<Map>> {
        return dataSource.readData()
    }

    override suspend fun insertData(maps: List<Map>) {
        dataSource.insertData(maps)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Map>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getMaps(): Response<List<Map>> {
        return dataSource.getMaps()
    }

}