package eu.seijindemon.student_iee_ihu.data.offer

import eu.seijindemon.student_iee_ihu.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class OfferRepositoryImpl @Inject constructor(
    private val dataSource: OfferDataSource
) : OfferRepository {

    override fun readData(): Flow<List<Offer>> {
        return dataSource.readData()
    }

    override suspend fun insertData(offers: List<Offer>) {
        dataSource.insertData(offers)
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Offer>> {
        return dataSource.searchDatabase(searchQuery)
    }

    override suspend fun getOffers(): Response<List<Offer>> {
        return dataSource.getOffers()
    }

}