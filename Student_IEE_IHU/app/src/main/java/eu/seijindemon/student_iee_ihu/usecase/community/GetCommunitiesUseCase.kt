package eu.seijindemon.student_iee_ihu.usecase.community

import eu.seijindemon.student_iee_ihu.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.framework.community.model.Community
import retrofit2.Response
import javax.inject.Inject

class GetCommunitiesUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke() : Response<List<Community>> {
        return repository.getCommunities()
    }
}