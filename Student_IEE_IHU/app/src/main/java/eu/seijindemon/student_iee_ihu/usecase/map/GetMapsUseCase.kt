package eu.seijindemon.student_iee_ihu.usecase.map

import eu.seijindemon.student_iee_ihu.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import retrofit2.Response
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(
    private val repository: MapRepository
) {
    suspend operator fun invoke() : Response<List<Map>> {
        return repository.getMaps()
    }
}