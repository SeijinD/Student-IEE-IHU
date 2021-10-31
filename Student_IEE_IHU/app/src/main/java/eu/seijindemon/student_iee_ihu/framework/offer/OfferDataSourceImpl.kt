package eu.seijindemon.student_iee_ihu.framework.offer

import eu.seijindemon.student_iee_ihu.data.offer.OfferDataSource
import eu.seijindemon.student_iee_ihu.framework.api.DbApi
import eu.seijindemon.student_iee_ihu.framework.db.AppDb
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class OfferDataSourceImpl @Inject constructor(
    private val appDb: AppDb,
    private val api: DbApi
) : OfferDataSource {

    override fun readData(): Flow<List<Offer>> {
        return appDb.offerDao().readData()
    }

    override suspend fun insertData(offers: List<Offer>) {
        appDb.offerDao().insertData(offers)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Offer>> {
        return appDb.offerDao().searchDatabase(searchQuery)
    }

    override suspend fun getOffers(): Response<List<Offer>> {
        return api.getOffers()
    }

}