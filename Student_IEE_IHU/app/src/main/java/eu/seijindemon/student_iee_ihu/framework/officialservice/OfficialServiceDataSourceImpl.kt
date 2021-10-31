package eu.seijindemon.student_iee_ihu.framework.officialservice

import eu.seijindemon.student_iee_ihu.data.officialservice.OfficialServiceDataSource
import eu.seijindemon.student_iee_ihu.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class OfficialServiceDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : OfficialServiceDataSource {

    override fun readData(): Flow<List<OfficialService>> {
        return appDb.officialServiceDao().readData()
    }

    override suspend fun insertData(officialServices: List<OfficialService>) {
        appDb.officialServiceDao().insertData(officialServices)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<OfficialService>> {
        return appDb.officialServiceDao().searchDatabase(searchQuery)
    }

    override suspend fun getOfficialServices(): Response<List<OfficialService>> {
        return api.getOfficialServices()
    }

}