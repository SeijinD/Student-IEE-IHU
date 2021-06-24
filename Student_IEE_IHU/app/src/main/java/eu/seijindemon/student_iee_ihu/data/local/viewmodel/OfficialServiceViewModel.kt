package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.OfficialService
import eu.seijindemon.student_iee_ihu.data.repository.OfficialServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class OfficialServiceViewModel(private val  repository: OfficialServiceRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(officialService: OfficialService) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(officialService)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<OfficialService>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}

class OfficialServiceViewModelFactory(private val repository: OfficialServiceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfficialServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfficialServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}