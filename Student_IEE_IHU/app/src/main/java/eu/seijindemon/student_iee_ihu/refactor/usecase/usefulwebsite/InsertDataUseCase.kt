package eu.seijindemon.student_iee_ihu.refactor.usecase.usefulwebsite

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    suspend operator fun invoke(usefulWebsite: List<UsefulWebsite>) {
        repository.insertData(usefulWebsite)
    }
}