package eu.seijindemon.student_iee_ihu.usecase.usefulwebsite

import eu.seijindemon.student_iee_ihu.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<UsefulWebsite>> {
        return repository.searchDatabase(searchQuery)
    }
}