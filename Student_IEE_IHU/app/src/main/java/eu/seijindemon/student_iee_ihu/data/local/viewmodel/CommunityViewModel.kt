package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import android.util.Log
import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.data.repository.CommunityRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class CommunityViewModel(private val  repository: CommunityRepository): ViewModel() {

    fun readData(): LiveData<List<Community>> {
        return repository.readData().asLiveData()
    }

    fun insertData(communities: List<Community>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(communities)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Community>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    fun communityFbGroups(): LiveData<List<Community>> {
        return repository.communityFbGroups().asLiveData()
    }

    fun communityFbPages(): LiveData<List<Community>> {
        return repository.communityFbPages().asLiveData()
    }

    fun communityDiscordServers(): LiveData<List<Community>> {
        return repository.communityDiscordServers().asLiveData()
    }

    fun communityOther(): LiveData<List<Community>> {
        return repository.communityOther().asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getCommunities() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getCommunities()
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

class CommunityViewModelFactory(private val repository: CommunityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommunityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}