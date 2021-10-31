package eu.seijindemon.student_iee_ihu.refactor.usecase.unofficilianservice

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.unofficilianservice.UnofficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.unofficialservice.model.UnofficialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: UnofficialServiceRepository
) {
    operator fun invoke() : Flow<List<UnofficialService>> {
        return repository.readData()
    }
}