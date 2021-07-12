package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.UnofficialService
import eu.seijindemon.student_iee_ihu.data.repository.UnofficialServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.IllegalArgumentException

class UnofficialServiceViewModel(private val  repository: UnofficialServiceRepository): ViewModel() {

    fun readData(): LiveData<List<UnofficialService>> {
        return repository.readData().asLiveData()
    }

    fun insertData(unofficialServices: List<UnofficialService>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(unofficialServices)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UnofficialService>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    val myResponse: MutableLiveData<Response<List<UnofficialService>>> = MutableLiveData()

    fun getUnofficialServices() {
        viewModelScope.launch {
            val response = repository.getUnofficialServices()
            myResponse.value = response
        }
    }

}

class UnofficialServiceViewModelFactory(private val repository: UnofficialServiceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnofficialServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnofficialServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}