package eu.seijindemon.student_iee_ihu.usecase.offer

import eu.seijindemon.student_iee_ihu.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<Offer>> {
        return repository.searchDatabase(searchQuery)
    }
}