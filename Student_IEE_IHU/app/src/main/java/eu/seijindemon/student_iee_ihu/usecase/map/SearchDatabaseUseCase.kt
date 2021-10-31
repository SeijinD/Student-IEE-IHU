package eu.seijindemon.student_iee_ihu.usecase.map

import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: MapRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<Map>> {
        return repository.searchDatabase(searchQuery)
    }
}