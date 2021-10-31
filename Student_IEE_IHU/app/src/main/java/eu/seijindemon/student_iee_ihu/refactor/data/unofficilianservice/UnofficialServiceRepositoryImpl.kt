package eu.seijindemon.student_iee_ihu.refactor.data.unofficilianservice

import eu.seijindemon.student_iee_ihu.refactor.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UnofficialServiceRepositoryImpl @Inject constructor(
    private val dataSource: UnofficialServiceDataSource
) : UnofficialServiceRepository {

    override fun readData(): Flow<List<UnofficialService>> {
        return dataSource.readData()
    }

    override suspend fun insertData(unofficialServices: List<UnofficialService>) {
        dataSource.insertData(unofficialServices)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getUnofficialServices(): Response<List<UnofficialService>> {
        return dataSource.getUnofficialServices()
    }

}