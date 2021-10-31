package eu.seijindemon.student_iee_ihu.refactor.usecase.usefulwebsite

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<UsefulWebsite>> {
        return repository.searchDatabase(searchQuery)
    }
}