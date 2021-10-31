package eu.seijindemon.student_iee_ihu.ui.find.unofficial_services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.unofficialservice.model.UnofficialService
import eu.seijindemon.student_iee_ihu.usecase.unofficilianservice.GetUnofficialServicesUseCase
import eu.seijindemon.student_iee_ihu.usecase.unofficilianservice.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.unofficilianservice.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.unofficilianservice.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UnofficialServiceViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getUnofficialServicesUseCase: GetUnofficialServicesUseCase
): ViewModel() {

    fun readData(): LiveData<List<UnofficialService>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(unofficialServices: List<UnofficialService>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(unofficialServices)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UnofficialService>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getUnofficialServices() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getUnofficialServicesUseCase.invoke()
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