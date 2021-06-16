package eu.seijindemon.student_iee_ihu.data.repository


import eu.seijindemon.student_iee_ihu.data.local.dao.OfferDao
import eu.seijindemon.student_iee_ihu.data.model.Offer
import kotlinx.coroutines.flow.Flow

class OfferRepository(private val offerDao: OfferDao) {

    fun readData(): Flow<List<Offer>> {
        return offerDao.readData()
    }

    suspend fun insertData(offer: Offer) {
        offerDao.insertData(offer)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Offer>> {
        return offerDao.searchDatabase(searchQuery)
    }

}