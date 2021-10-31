package eu.seijindemon.student_iee_ihu.usecase.officialservice

import eu.seijindemon.student_iee_ihu.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.officialservice.model.OfficialService
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    suspend operator fun invoke(officialService: List<OfficialService>) {
        repository.insertData(officialService)
    }
}