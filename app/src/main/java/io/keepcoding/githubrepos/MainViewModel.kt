package io.keepcoding.githubrepos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.keepcoding.githubrepos.model.Repo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val repository: MainRepository
): ViewModel(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val reposState = MutableLiveData<List<Repo>>()
    val isLoadingState = MutableLiveData<Boolean>()

    init {
        isLoadingState.value = false
    }

    fun loadRepos(username: String) {
        launch {
            isLoadingState.value = true
            val repos = withContext(Dispatchers.IO) { repository.getRepos(username) }
            // Sigo en el Main thread y ya ha terminado repos, o sea, la API
            // Manda los repos a la vista
            reposState.value = repos
            isLoadingState.value = false
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}