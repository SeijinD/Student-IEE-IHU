package eu.seijindemon.student_iee_ihu.usecase.teacher

import eu.seijindemon.student_iee_ihu.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    operator fun invoke() : Flow<List<Teacher>> {
        return repository.readData()
    }
}