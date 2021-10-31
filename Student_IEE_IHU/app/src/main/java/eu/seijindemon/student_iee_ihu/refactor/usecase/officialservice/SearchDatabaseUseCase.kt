package eu.seijindemon.student_iee_ihu.refactor.usecase.officialservice

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.officialservice.OfficialServiceRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.officialservice.model.OfficialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: OfficialServiceRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<OfficialService>> {
        return repository.searchDatabase(searchQuery)
    }
}