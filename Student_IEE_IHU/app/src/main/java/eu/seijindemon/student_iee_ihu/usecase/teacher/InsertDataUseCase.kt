package eu.seijindemon.student_iee_ihu.usecase.teacher

import eu.seijindemon.student_iee_ihu.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(teacher: List<Teacher>) {
        repository.insertData(teacher)
    }
}