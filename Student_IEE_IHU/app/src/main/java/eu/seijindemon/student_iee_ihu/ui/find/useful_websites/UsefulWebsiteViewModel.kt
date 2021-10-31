package eu.seijindemon.student_iee_ihu.ui.find.useful_websites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.usefulwebsite.model.UsefulWebsite
import eu.seijindemon.student_iee_ihu.usecase.usefulwebsite.GetUsefulWebsitesUseCase
import eu.seijindemon.student_iee_ihu.usecase.usefulwebsite.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.usefulwebsite.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.usefulwebsite.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UsefulWebsiteViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getUsefulWebsitesUseCase: GetUsefulWebsitesUseCase
): ViewModel() {

    fun readData(): LiveData<List<UsefulWebsite>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(usefulWebsites: List<UsefulWebsite>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(usefulWebsites)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UsefulWebsite>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getUsefulWebsites() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getUsefulWebsitesUseCase.invoke()
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