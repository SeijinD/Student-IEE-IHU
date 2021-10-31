package eu.seijindemon.student_iee_ihu.refactor.usecase.community

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import retrofit2.Response
import javax.inject.Inject

class GetCommunitiesUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke() : Response<List<Community>> {
        return repository.getCommunities()
    }
}