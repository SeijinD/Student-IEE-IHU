package eu.seijindemon.student_iee_ihu.usecase.community

import eu.seijindemon.student_iee_ihu.domain.community.CommunityRepository
import eu.seijindemon.student_iee_ihu.framework.community.model.Community
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(communities: List<Community>) {
        repository.insertData(communities)
    }
}