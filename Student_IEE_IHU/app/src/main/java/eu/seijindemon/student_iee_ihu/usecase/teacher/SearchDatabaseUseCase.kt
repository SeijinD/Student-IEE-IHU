package eu.seijindemon.student_iee_ihu.usecase.teacher

import eu.seijindemon.student_iee_ihu.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDatabaseUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    operator fun invoke(searchQuery: String) : Flow<List<Teacher>> {
        return repository.searchDatabase(searchQuery)
    }
}