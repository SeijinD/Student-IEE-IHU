package eu.seijindemon.student_iee_ihu.usecase.usefulwebsite

import eu.seijindemon.student_iee_ihu.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    suspend operator fun invoke(usefulWebsite: List<UsefulWebsite>) {
        repository.insertData(usefulWebsite)
    }
}