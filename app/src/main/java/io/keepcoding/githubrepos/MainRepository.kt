package io.keepcoding.githubrepos

import io.keepcoding.githubrepos.model.Repo

interface MainRepository {

    suspend fun getRepos(username: String): List<Repo>

}