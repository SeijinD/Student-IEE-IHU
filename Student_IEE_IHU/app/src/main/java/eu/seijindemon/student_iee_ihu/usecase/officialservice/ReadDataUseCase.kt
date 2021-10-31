package eu.seijindemon.student_iee_ihu.usecase.officialservice

import eu.seijindemon.student_iee_ihu.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    operator fun invoke() : Flow<List<OfficialService>> {
        return repository.readData()
    }
}