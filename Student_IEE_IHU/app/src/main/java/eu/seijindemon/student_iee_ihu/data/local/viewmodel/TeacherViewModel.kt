package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import eu.seijindemon.student_iee_ihu.data.repository.TeacherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    val myResponse: MutableLiveData<Response<List<Teacher>>> = MutableLiveData()

    fun getTeachers() {
        viewModelScope.launch {
            val response = repository.getTeachers()
            myResponse.value = response
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