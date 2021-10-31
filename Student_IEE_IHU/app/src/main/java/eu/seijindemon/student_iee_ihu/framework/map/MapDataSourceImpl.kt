package eu.seijindemon.student_iee_ihu.framework.map

import eu.seijindemon.student_iee_ihu.data.map.MapDataSource
import eu.seijindemon.student_iee_ihu.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MapDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : MapDataSource {

    override fun readData(): Flow<List<Map>> {
        return appDb.mapDao().readData()
    }

    override suspend fun insertData(maps: List<Map>) {
        appDb.mapDao().insertData(maps)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Map>> {
        return appDb.mapDao().searchDatabase(searchQuery)
    }

    override suspend fun getMaps(): Response<List<Map>> {
        return api.getMaps()
    }

}