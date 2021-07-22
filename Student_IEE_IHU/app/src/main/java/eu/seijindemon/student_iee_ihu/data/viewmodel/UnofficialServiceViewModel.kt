package eu.seijindemon.student_iee_ihu.data.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.UnofficialService
import eu.seijindemon.student_iee_ihu.data.repository.UnofficialServiceRepository
import kotlinx.coroutines.*
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

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getUnofficialServices() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getUnofficialServices()
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

class UnofficialServiceViewModelFactory(private val repository: UnofficialServiceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnofficialServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnofficialServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}