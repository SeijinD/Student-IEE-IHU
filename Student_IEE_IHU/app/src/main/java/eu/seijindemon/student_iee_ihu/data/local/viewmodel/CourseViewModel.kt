package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.repository.CourseRepository
import kotlinx.coroutines.*
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

    fun getCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCourses()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        insertData(it)
                    }
                }
                else {
                    Log.d("Response", response.errorBody().toString())
                }
            }
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