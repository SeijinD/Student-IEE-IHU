package eu.seijindemon.student_iee_ihu.domain.offer


import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OfferRepository {

    fun readData(): Flow<List<Offer>>

    suspend fun insertData(offers: List<Offer>)

    fun searchDatabase(searchQuery: String): Flow<List<Offer>>

    suspend fun getOffers(): Response<List<Offer>>

}