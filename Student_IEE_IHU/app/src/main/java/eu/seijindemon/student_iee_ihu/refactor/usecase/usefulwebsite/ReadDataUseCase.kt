package eu.seijindemon.student_iee_ihu.refactor.usecase.usefulwebsite

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.usefulwebsite.UsefulWebsiteRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.usefulwebsite.model.UsefulWebsite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: UsefulWebsiteRepository
) {
    operator fun invoke() : Flow<List<UsefulWebsite>> {
        return repository.readData()
    }
}