package eu.seijindemon.student_iee_ihu.refactor.usecase.offer

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.model.Offer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    operator fun invoke() : Flow<List<Offer>> {
        return repository.readData()
    }
}