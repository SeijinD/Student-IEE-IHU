package eu.seijindemon.student_iee_ihu.refactor.data.unofficilianservice


import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UnofficialServiceDataSource{

    fun readData(): Flow<List<UnofficialService>>

    suspend fun insertData(unofficialServices: List<UnofficialService>)

    fun searchDatabase(searchQuery: String): Flow<List<UnofficialService>>

    suspend fun getUnofficialServices(): Response<List<UnofficialService>>

}