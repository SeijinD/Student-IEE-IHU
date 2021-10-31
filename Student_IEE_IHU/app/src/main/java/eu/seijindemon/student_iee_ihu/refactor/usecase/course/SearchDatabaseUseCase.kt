package eu.seijindemon.student_iee_ihu.refactor.usecase.course

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<Course>> {
        return repository.searchDatabase(searchQuery)
    }
}