package eu.seijindemon.student_iee_ihu.ui.dashboard.find.courses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.course.model.Course
import eu.seijindemon.student_iee_ihu.ui.base.BaseViewModel
import eu.seijindemon.student_iee_ihu.usecase.course.GetCoursesUseCase
import eu.seijindemon.student_iee_ihu.usecase.course.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.course.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.course.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getCoursesUseCase: GetCoursesUseCase
): BaseViewModel() {

    fun readData(): LiveData<List<Course>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(courses: List<Course>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(courses)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Course>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getCourses() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getCoursesUseCase.invoke()
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