package eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite

import eu.seijindemon.student_iee_ihu.refactor.data.usefulwebsite.UsefulWebsiteDataSource
import eu.seijindemon.student_iee_ihu.refactor.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.refactor.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UsefulWebsiteDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : UsefulWebsiteDataSource {

    override fun readData(): Flow<List<UsefulWebsite>> {
        return appDb.usefulWebsiteDao().readData()
    }

    override suspend fun insertData(usefulWebsites: List<UsefulWebsite>) {
        appDb.usefulWebsiteDao().insertData(usefulWebsites)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<UsefulWebsite>> {
        return appDb.usefulWebsiteDao().searchDatabase(searchQuery)
    }

    override suspend fun getUsefulWebsites(): Response<List<UsefulWebsite>> {
        return api.getUsefulWebsites()
    }

}