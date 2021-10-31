package eu.seijindemon.student_iee_ihu.refactor.usecase.offer

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.offer.OfferRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.offer.model.Offer
import retrofit2.Response
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val repository: OfferRepository
) {
    suspend operator fun invoke() : Response<List<Offer>> {
        return repository.getOffers()
    }
}