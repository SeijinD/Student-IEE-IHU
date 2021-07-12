package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.UsefulWebsite
import eu.seijindemon.student_iee_ihu.data.repository.UsefulWebsiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.IllegalArgumentException

class UsefulWebsiteViewModel(private val  repository: UsefulWebsiteRepository): ViewModel() {

    fun readData(): LiveData<List<UsefulWebsite>> {
        return repository.readData().asLiveData()
    }

    fun insertData(usefulWebsites: List<UsefulWebsite>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(usefulWebsites)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UsefulWebsite>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    val myResponse: MutableLiveData<Response<List<UsefulWebsite>>> = MutableLiveData()

    fun getUsefulWebsites() {
        viewModelScope.launch {
            val response = repository.getUsefulWebsites()
            myResponse.value = response
        }
    }

}

class UsefulWebsiteViewModelFactory(private val repository: UsefulWebsiteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsefulWebsiteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsefulWebsiteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}