package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Teacher
import eu.seijindemon.student_iee_ihu.data.repository.TeacherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TeacherViewModel(private val  repository: TeacherRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(teacher: Teacher) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(teacher)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Teacher>> {
        return repository.searchDatabase(searchQuery).asLiveData()
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