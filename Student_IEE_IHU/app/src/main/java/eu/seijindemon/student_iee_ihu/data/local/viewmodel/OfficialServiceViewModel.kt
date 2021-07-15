package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.OfficialService
import eu.seijindemon.student_iee_ihu.data.repository.OfficialServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun getOfficialServices() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getOfficialServices()
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

class OfficialServiceViewModelFactory(private val repository: OfficialServiceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfficialServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfficialServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}