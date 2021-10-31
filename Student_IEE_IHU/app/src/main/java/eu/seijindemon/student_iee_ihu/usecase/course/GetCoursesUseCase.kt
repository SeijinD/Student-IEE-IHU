package eu.seijindemon.student_iee_ihu.usecase.course

import eu.seijindemon.student_iee_ihu.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.framework.course.model.Course
import retrofit2.Response
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke() : Response<List<Course>> {
        return repository.getCourses()
    }
}