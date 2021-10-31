package eu.seijindemon.student_iee_ihu.refactor.usecase.teacher

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.teacher.TeacherRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.teacher.model.Teacher
import retrofit2.Response
import javax.inject.Inject

class GetTeachersUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke() : Response<List<Teacher>> {
        return repository.getTeachers()
    }
}