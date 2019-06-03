package io.keepcoding.githubrepos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.githubrepos.model.Repo
import kotlinx.android.synthetic.main.view_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    val repoList: MutableList<Repo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun setItems(repo: List<Repo>) {
        repoList.clear()
        repoList.addAll(repo)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo) {
            with(itemView) {
                repoName.text = repo.nameOfTheRepo
            }
        }

    }

}