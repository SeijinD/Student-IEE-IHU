package eu.seijindemon.student_iee_ihu.usecase.teacher

import eu.seijindemon.student_iee_ihu.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import retrofit2.Response
import javax.inject.Inject

class GetTeachersUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke() : Response<List<Teacher>> {
        return repository.getTeachers()
    }
}