package eu.seijindemon.student_iee_ihu.usecase.course

import eu.seijindemon.student_iee_ihu.domain.course.CourseRepository
import eu.seijindemon.student_iee_ihu.framework.course.model.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDataUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    operator fun invoke() : Flow<List<Course>> {
        return repository.readData()
    }
}