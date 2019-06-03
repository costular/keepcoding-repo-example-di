package io.keepcoding.githubrepos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.keepcoding.githubrepos.api.GithubService
import io.keepcoding.githubrepos.model.Repo
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
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