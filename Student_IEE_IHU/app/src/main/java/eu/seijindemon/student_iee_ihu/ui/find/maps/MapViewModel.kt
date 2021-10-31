package eu.seijindemon.student_iee_ihu.ui.find.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.map.model.Map
import eu.seijindemon.student_iee_ihu.usecase.map.GetMapsUseCase
import eu.seijindemon.student_iee_ihu.usecase.map.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.map.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.map.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getMapsUseCase: GetMapsUseCase
): ViewModel() {

    fun readData(): LiveData<List<Map>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(maps: List<Map>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(maps)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Map>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getMaps() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getMapsUseCase.invoke()
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