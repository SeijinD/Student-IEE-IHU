package eu.seijindemon.student_iee_ihu.refactor.ui.find.official_services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.officialservice.model.OfficialService
import eu.seijindemon.student_iee_ihu.usecase.officialservice.GetOfficialServicesUseCase
import eu.seijindemon.student_iee_ihu.usecase.officialservice.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.officialservice.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.officialservice.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OfficialServiceViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getOfficialServicesUseCase: GetOfficialServicesUseCase
): ViewModel() {

    fun readData(): LiveData<List<OfficialService>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(officialServices: List<OfficialService>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(officialServices)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<OfficialService>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getOfficialServices() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getOfficialServicesUseCase.invoke()
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