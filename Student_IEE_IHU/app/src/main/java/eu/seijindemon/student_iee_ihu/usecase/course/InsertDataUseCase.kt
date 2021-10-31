package eu.seijindemon.student_iee_ihu.usecase.course

import eu.seijindemon.student_iee_ihu.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.framework.course.model.Course
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courses: List<Course>) {
        repository.insertData(courses)
    }
}