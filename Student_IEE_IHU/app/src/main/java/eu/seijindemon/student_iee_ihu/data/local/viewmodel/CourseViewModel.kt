package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CourseViewModel(private val  repository: CourseRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(course)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Course>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}

class CourseViewModelFactory(private val repository: CourseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}