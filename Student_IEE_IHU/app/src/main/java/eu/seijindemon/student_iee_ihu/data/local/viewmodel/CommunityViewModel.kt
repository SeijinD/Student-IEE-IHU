package eu.seijindemon.student_iee_ihu.data.local.viewmodel

import androidx.lifecycle.*
import eu.seijindemon.student_iee_ihu.data.model.Community
import eu.seijindemon.student_iee_ihu.data.repository.CommunityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CommunityViewModel(private val  repository: CommunityRepository): ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(community: Community) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(community)
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