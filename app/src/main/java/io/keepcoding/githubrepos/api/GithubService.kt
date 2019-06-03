package io.keepcoding.githubrepos.api

import io.keepcoding.githubrepos.model.Repo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Deferred<List<Repo>>

}