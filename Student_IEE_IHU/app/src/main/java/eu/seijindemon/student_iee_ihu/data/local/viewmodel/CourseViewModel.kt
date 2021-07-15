package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.data.model.Course
import eu.seijindemon.student_iee_ihu.data.repository.CourseRepository
import kotlinx.coroutines.*
import www.sanju.motiontoast.MotionToast
import java.lang.IllegalArgumentException
import kotlin.coroutines.coroutineContext

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

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getCourses() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
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