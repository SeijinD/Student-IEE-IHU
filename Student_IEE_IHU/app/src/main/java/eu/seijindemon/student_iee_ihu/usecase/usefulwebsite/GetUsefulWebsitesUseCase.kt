package eu.seijindemon.student_iee_ihu.usecase.usefulwebsite

import eu.seijindemon.student_iee_ihu.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import retrofit2.Response
import javax.inject.Inject

class GetUsefulWebsitesUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    suspend operator fun invoke() : Response<List<UsefulWebsite>> {
        return repository.getUsefulWebsites()
    }
}