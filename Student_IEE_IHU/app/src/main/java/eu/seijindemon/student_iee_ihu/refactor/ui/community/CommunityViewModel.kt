package eu.seijindemon.student_iee_ihu.refactor.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.student_iee_ihu.framework.community.model.Community
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseViewModel
import eu.seijindemon.student_iee_ihu.usecase.community.*
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val readDataUseCase: ReadDataUseCase,
    private val insertDataUseCase: InsertDataUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val communityFbGroupsUseCase: CommunityFbGroupsUseCase,
    private val communityFbPagesUseCase: CommunityFbPagesUseCase,
    private val communityDiscordServersUseCase: CommunityDiscordServersUseCase,
    private val communityOtherUseCase: CommunityOtherUseCase,
    private val getCommunitiesUseCase: GetCommunitiesUseCase
): BaseViewModel() {

    fun readData(): LiveData<List<Community>> {
        return readDataUseCase.invoke().asLiveData()
    }

    fun insertData(communities: List<Community>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.invoke(communities)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Community>>{
         return searchDatabaseUseCase.invoke(searchQuery).asLiveData()
    }

    fun communityFbGroups(): LiveData<List<Community>> {
        return communityFbGroupsUseCase.invoke().asLiveData()
    }

    fun communityFbPages(): LiveData<List<Community>> {
        return communityFbPagesUseCase.invoke().asLiveData()
    }

    fun communityDiscordServers(): LiveData<List<Community>>{
        return communityDiscordServersUseCase.invoke().asLiveData()
    }

    fun communityOther(): LiveData<List<Community>> {
        return communityOtherUseCase.invoke().asLiveData()
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getCommunities() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = getCommunitiesUseCase.invoke()
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