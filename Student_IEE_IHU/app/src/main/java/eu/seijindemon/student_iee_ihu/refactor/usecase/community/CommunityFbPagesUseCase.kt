package eu.seijindemon.student_iee_ihu.refactor.usecase.community

import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityFbPagesUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    operator fun invoke() : Flow<List<Community>> {
        return repository.communityFbPages()
    }
}