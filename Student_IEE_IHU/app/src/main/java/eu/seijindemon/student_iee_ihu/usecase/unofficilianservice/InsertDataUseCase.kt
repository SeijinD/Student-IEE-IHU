package eu.seijindemon.student_iee_ihu.usecase.unofficilianservice

import eu.seijindemon.student_iee_ihu.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: UnofficialServiceRepository
) {
    suspend operator fun invoke(unofficialService: List<UnofficialService>) {
        repository.insertData(unofficialService)
    }
}