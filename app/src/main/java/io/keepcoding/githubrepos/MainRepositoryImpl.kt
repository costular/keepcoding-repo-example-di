package io.keepcoding.githubrepos

import io.keepcoding.githubrepos.api.GithubService
import io.keepcoding.githubrepos.model.Repo

class MainRepositoryImpl(
    private val githubService: GithubService
) : MainRepository {

    override suspend fun getRepos(username: String): List<Repo> =
            githubService.getRepos(username).await()

}