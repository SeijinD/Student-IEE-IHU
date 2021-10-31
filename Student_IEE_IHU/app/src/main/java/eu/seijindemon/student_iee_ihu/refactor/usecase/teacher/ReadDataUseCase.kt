package eu.seijindemon.student_iee_ihu.refactor.usecase.teacher

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    operator fun invoke() : Flow<List<Teacher>> {
        return repository.readData()
    }
}