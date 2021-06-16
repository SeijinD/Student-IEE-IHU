package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Offer
import eu.seijindemon.student_iee_ihu.data.repository.OfferRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class OfferViewModel(private val  repository: OfferRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(offer: Offer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(offer)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Offer>> {
        return repository.searchDatabase(searchQuery).asLiveData()
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