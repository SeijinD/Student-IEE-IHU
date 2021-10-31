package eu.seijindemon.student_iee_ihu.refactor.usecase.unofficilianservice

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.model.UnofficialService
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: UnofficialServiceRepository
) {
    suspend operator fun invoke(unofficialService: List<UnofficialService>) {
        repository.insertData(unofficialService)
    }
}