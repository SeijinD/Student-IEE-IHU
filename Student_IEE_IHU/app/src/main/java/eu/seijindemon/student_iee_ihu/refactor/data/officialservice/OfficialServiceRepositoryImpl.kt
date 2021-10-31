package eu.seijindemon.student_iee_ihu.refactor.data.officialservice

import eu.seijindemon.student_iee_ihu.refactor.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class OfficialServiceRepositoryImpl @Inject constructor(
    private val dataSource: OfficialServiceDataSource
) : OfficialServiceRepository {

    override fun readData(): Flow<List<OfficialService>> {
        return dataSource.readData()
    }

    override suspend fun insertData(officialServices: List<OfficialService>) {
        dataSource.insertData(officialServices)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<OfficialService>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getOfficialServices(): Response<List<OfficialService>> {
        return dataSource.getOfficialServices()
    }

}