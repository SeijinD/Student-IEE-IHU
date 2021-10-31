package eu.seijindemon.student_iee_ihu.refactor.usecase.officialservice

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import retrofit2.Response
import javax.inject.Inject

class GetOfficialServicesUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    suspend operator fun invoke() : Response<List<OfficialService>> {
        return repository.getOfficialServices()
    }
}