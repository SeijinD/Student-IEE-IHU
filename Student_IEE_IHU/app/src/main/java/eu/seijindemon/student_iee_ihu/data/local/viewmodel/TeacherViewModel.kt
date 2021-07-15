package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import eu.seijindemon.student_iee_ihu.data.repository.TeacherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.IllegalArgumentException

class TeacherViewModel(private val  repository: TeacherRepository): ViewModel() {

    fun readData(): LiveData<List<Teacher>> {
        return  repository.readData().asLiveData()
    }

    fun insertData(teachers: List<Teacher>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(teachers)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Teacher>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    fun getTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getTeachers()
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

class TeacherViewModelFactory(private val repository: TeacherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeacherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeacherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}