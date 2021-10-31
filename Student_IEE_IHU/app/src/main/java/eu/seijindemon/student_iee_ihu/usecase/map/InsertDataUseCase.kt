package eu.seijindemon.student_iee_ihu.usecase.map

import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: MapRepository
) {
    suspend operator fun invoke(maps: List<Map>) {
        repository.insertData(maps)
    }
}