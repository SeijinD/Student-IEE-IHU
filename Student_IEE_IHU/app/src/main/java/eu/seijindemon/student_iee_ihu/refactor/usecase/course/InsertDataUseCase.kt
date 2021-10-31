package eu.seijindemon.student_iee_ihu.refactor.usecase.course

import dagger.hilt.android.scopes.ActivityScoped
import eu.seijindemon.student_iee_ihu.refactor.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.refactor.framework.course.model.Course
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courses: List<Course>) {
        repository.insertData(courses)
    }
}