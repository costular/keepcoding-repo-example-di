package io.keepcoding.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.githubrepos.di.ViewModelFactory
import io.keepcoding.githubrepos.model.Repo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val adapter = RepoAdapter()

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecycler()
        bindActions()
        bindState()
    }

    private fun inject() {
        (application as MainApplication).component.inject(this)
    }

    private fun setUpRecycler() {
        with (recycler) {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
    }

    private fun bindState() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        with(viewModel) {
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
            viewModel.loadRepos(editText.text.toString())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setRepos(repos: List<Repo>) {
        adapter.setItems(repos)
    }

}
