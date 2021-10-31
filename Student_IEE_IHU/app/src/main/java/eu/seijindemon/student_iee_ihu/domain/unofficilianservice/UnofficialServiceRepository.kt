package eu.seijindemon.student_iee_ihu.domain.unofficilianservice


import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UnofficialServiceRepository{

    fun readData(): Flow<List<UnofficialService>>

    suspend fun insertData(unofficialServices: List<UnofficialService>)

    fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>>

    suspend fun getUnofficialServices(): Response<List<UnofficialService>>

}