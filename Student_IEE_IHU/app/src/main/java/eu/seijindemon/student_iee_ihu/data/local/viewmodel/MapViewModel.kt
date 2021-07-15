package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Map
import eu.seijindemon.student_iee_ihu.data.repository.MapRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.IllegalArgumentException

class MapViewModel(private val  repository: MapRepository): ViewModel() {

    fun readData(): LiveData<List<Map>> {
        return repository.readData().asLiveData()
    }

    fun insertData(maps: List<Map>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(maps)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Map>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    fun getMaps() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getMaps()
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

class MapViewModelFactory(private val repository: MapRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}