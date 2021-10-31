package eu.seijindemon.student_iee_ihu.refactor.usecase.offer

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.model.Offer
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    suspend operator fun invoke(offers: List<Offer>) {
        repository.insertData(offers)
    }
}