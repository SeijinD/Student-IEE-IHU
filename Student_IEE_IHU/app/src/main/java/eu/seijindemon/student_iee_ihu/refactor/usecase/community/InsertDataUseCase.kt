package eu.seijindemon.student_iee_ihu.refactor.usecase.community

import eu.seijindemon.student_iee_ihu.refactor.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.community.model.Community
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(communities: List<Community>) {
        repository.insertData(communities)
    }
}