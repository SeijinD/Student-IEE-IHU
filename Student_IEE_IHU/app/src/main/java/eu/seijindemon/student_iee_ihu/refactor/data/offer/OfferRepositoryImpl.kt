package eu.seijindemon.student_iee_ihu.refactor.data.offer

import eu.seijindemon.student_iee_ihu.refactor.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.model.Offer
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