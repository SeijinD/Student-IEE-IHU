package eu.seijindemon.student_iee_ihu.usecase.unofficilianservice

import eu.seijindemon.student_iee_ihu.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: UnofficialServiceRepository
) {
    operator fun invoke() : Flow<List<UnofficialService>> {
        return repository.readData()
    }
}