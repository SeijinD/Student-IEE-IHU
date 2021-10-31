package eu.seijindemon.student_iee_ihu.refactor.usecase.map

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.map.MapRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.map.model.Map
import retrofit2.Response
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(
    private val repository: MapRepository
) {
    suspend operator fun invoke() : Response<List<Map>> {
        return repository.getMaps()
    }
}