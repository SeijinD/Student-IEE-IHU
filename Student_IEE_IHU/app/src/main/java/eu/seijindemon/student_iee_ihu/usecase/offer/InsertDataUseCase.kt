package eu.seijindemon.student_iee_ihu.usecase.offer

import eu.seijindemon.student_iee_ihu.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    suspend operator fun invoke(offers: List<Offer>) {
        repository.insertData(offers)
    }
}