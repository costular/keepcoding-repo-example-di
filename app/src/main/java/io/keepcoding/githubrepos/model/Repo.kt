package io.keepcoding.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Long,
    @SerializedName("name") val nameOfTheRepo: String,
    @SerializedName("full_name") val fullName: String
)