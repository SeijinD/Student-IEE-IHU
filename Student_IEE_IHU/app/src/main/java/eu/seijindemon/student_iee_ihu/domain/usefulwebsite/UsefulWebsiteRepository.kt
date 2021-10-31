package eu.seijindemon.student_iee_ihu.domain.usefulwebsite


import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UsefulWebsiteRepository {

    fun readData(): Flow<List<UsefulWebsite>>

    suspend fun insertData(usefulWebsites: List<UsefulWebsite>)

    fun searchDatabase(searchQuery: String): Flow<List<UsefulWebsite>>

    suspend fun getUsefulWebsites(): Response<List<UsefulWebsite>>

}