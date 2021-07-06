package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import retrofit2.Response
import java.lang.IllegalArgumentException

class CourseViewModel(private val  repository: CourseRepository): ViewModel() {

    fun readData(): LiveData<List<Course>> {
        return repository.readData().asLiveData()
    }

    fun insertData(courses: List<Course>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(courses)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Course>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    val myResponse: MutableLiveData<Response<List<Course>>> = MutableLiveData()

    fun getCourses() {
        viewModelScope.launch {
            val response = repository.getCourses()
            myResponse.value = response
        }
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