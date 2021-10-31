package eu.seijindemon.student_iee_ihu.refactor.usecase.community

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityDiscordServersUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    operator fun invoke() : Flow<List<Community>> {
        return repository.communityDiscordServers()
    }
}