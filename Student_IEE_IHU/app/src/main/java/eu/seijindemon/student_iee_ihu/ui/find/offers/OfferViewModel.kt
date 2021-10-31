package eu.seijindemon.student_iee_ihu.ui.find.offers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.offer.model.Offer
import eu.seijindemon.student_iee_ihu.usecase.offer.GetOffersUseCase
import eu.seijindemon.student_iee_ihu.usecase.offer.InsertDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.offer.ReadDataUseCase
import eu.seijindemon.student_iee_ihu.usecase.offer.SearchDatabaseUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getOffersUseCase: GetOffersUseCase
): ViewModel() {

    fun readData(): LiveData<List<Offer>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(offers: List<Offer>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(offers)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Offer>> {
        return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getOffers() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getOffersUseCase.invoke()
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