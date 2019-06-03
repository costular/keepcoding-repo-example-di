package io.keepcoding.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.githubrepos.model.Repo
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val adapter = RepoAdapter()

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecycler()
        bindActions()
        bindState()
    }

    private fun setUpRecycler() {
        with (recycler) {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
    }

    private fun bindState() {
        with(mainViewModel) {
            isLoadingState.observe(this@MainActivity, Observer {
                showLoading(it!!)
            })
            reposState.observe(this@MainActivity, Observer {
                setRepos(it!!)
            })
        }
    }

    private fun bindActions() {
        button.setOnClickListener {
            mainViewModel.loadRepos(editText.text.toString())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setRepos(repos: List<Repo>) {
        adapter.setItems(repos)
    }

}
