package eu.seijindemon.student_iee_ihu.usecase.officialservice

import eu.seijindemon.student_iee_ihu.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.officialservice.model.OfficialService
import retrofit2.Response
import javax.inject.Inject

class GetOfficialServicesUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    suspend operator fun invoke() : Response<List<OfficialService>> {
        return repository.getOfficialServices()
    }
}