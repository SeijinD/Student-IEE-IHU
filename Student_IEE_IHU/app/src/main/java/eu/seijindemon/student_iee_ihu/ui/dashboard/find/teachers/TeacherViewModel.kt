package eu.seijindemon.student_iee_ihu.ui.dashboard.find.teachers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.teacher.model.Teacher
import eu.seijindemon.student_iee_ihu.ui.base.BaseViewModel
import eu.seijindemon.student_iee_ihu.usecase.teacher.GetTeachersUseCase
import eu.seijindemon.student_iee_ihu.usecase.teacher.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.teacher.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.teacher.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getTeachersUseCase: GetTeachersUseCase
): BaseViewModel() {

    fun readData(): LiveData<List<Teacher>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(teachers: List<Teacher>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(teachers)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Teacher>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getTeachers() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getTeachersUseCase.invoke()
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