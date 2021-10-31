package eu.seijindemon.student_iee_ihu.usecase.map

import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: MapRepository
) {
    operator fun invoke() : Flow<List<Map>> {
        return repository.readData()
    }
}