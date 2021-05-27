package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Map
import eu.seijindemon.student_iee_ihu.data.repository.MapRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MapViewModel(private val  repository: MapRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(map: Map) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(map)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Map>> {
        return repository.searchDatabase(searchQuery).asLiveData()
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