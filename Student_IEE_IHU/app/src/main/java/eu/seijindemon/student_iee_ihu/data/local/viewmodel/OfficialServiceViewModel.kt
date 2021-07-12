package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.OfficialService
import eu.seijindemon.student_iee_ihu.data.repository.OfficialServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.IllegalArgumentException

class OfficialServiceViewModel(private val  repository: OfficialServiceRepository): ViewModel() {

    fun readData(): LiveData<List<OfficialService>> {
        return repository.readData().asLiveData()
    }

    fun insertData(officialServices: List<OfficialService>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(officialServices)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<OfficialService>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    val myResponse: MutableLiveData<Response<List<OfficialService>>> = MutableLiveData()

    fun getOfficialServices() {
        viewModelScope.launch {
            val response = repository.getOfficialServices()
            myResponse.value = response
        }
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