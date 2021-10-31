package eu.seijindemon.student_iee_ihu.refactor.data.usefulwebsite

import eu.seijindemon.student_iee_ihu.refactor.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UsefulWebsiteRepositoryImpl @Inject constructor(
    private val dataSource: UsefulWebsiteDataSource
) : UsefulWebsiteRepository {

    override fun readData(): Flow<List<UsefulWebsite>> {
        return dataSource.readData()
    }

    override suspend fun insertData(usefulWebsites: List<UsefulWebsite>) {
        dataSource.insertData(usefulWebsites)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<UsefulWebsite>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getUsefulWebsites(): Response<List<UsefulWebsite>> {
        return dataSource.getUsefulWebsites()
    }

}