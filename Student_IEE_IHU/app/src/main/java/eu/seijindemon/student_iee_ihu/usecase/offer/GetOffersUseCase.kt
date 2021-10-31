package eu.seijindemon.student_iee_ihu.usecase.offer

import eu.seijindemon.student_iee_ihu.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import retrofit2.Response
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    suspend operator fun invoke() : Response<List<Offer>> {
        return repository.getOffers()
    }
}