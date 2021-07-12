package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.OfferDao
import eu.seijindemon.student_iee_ihu.data.model.Offer
import eu.seijindemon.student_iee_ihu.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class OfferRepository(private val offerDao: OfferDao) {

    fun readData(): Flow<List<Offer>> {
        return offerDao.readData()
    }

    suspend fun insertData(offers: List<Offer>) {
        offerDao.insertData(offers)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Offer>> {
        return offerDao.searchDatabase(searchQuery)
    }

    suspend fun getOffers(): Response<List<Offer>> {
        return RetrofitInstance.dbApi.getOffers()
    }

}