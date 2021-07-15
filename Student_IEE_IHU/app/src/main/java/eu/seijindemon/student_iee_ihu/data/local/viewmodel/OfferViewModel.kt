package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Offer
import eu.seijindemon.student_iee_ihu.data.repository.OfferRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class OfferViewModel(private val  repository: OfferRepository): ViewModel() {

    fun readData(): LiveData<List<Offer>> {
        return repository.readData().asLiveData()
    }

    fun insertData(offers: List<Offer>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(offers)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Offer>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getOffers() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getOffers()
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

class OfferViewModelFactory(private val repository: OfferRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfferViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}