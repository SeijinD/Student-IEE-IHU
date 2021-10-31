package eu.seijindemon.student_iee_ihu.framework.unofficialservice

import eu.seijindemon.student_iee_ihu.data.unofficilianservice.UnofficialServiceDataSource
import eu.seijindemon.student_iee_ihu.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UnofficialServiceDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : UnofficialServiceDataSource {

    override fun readData(): Flow<List<UnofficialService>> {
        return appDb.unofficialServiceDao().readData()
    }

    override suspend fun insertData(unofficialServices: List<UnofficialService>) {
        appDb.unofficialServiceDao().insertData(unofficialServices)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>> {
        return appDb.unofficialServiceDao().searchDatabase(searchQuery)
    }

    override suspend fun getUnofficialServices(): Response<List<UnofficialService>> {
        return api.getUnofficialServices()
    }

}