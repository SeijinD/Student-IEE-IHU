package eu.seijindemon.student_iee_ihu.refactor.usecase.officialservice

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    suspend operator fun invoke(officialService: List<OfficialService>) {
        repository.insertData(officialService)
    }
}