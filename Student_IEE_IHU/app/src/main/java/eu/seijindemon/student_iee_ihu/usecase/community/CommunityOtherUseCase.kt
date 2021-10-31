package eu.seijindemon.student_iee_ihu.usecase.community

import eu.seijindemon.student_iee_ihu.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.framework.community.model.Community
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityOtherUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    operator fun invoke() : Flow<List<Community>> {
        return repository.communityOther()
    }
}