package eu.seijindemon.student_iee_ihu.refactor.domain.officialservice


import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OfficialServiceRepository {

    fun readData(): Flow<List<OfficialService>>

    suspend fun insertData(officialServices: List<OfficialService>)

    fun searchDatabase(searchQuery: String): Flow<List<OfficialService>>

    suspend fun getOfficialServices(): Response<List<OfficialService>>

}