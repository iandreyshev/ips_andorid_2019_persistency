package psgroup.github.io.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_list.*
import psgroup.github.io.R
import psgroup.github.io.presentation.adapter.ListAdapter
import psgroup.github.io.viewModel.ListViewModel

class ListActivity : AppCompatActivity() {

    private val mListViewModel by lazy { ViewModelProviders.of(this).get(ListViewModel::class.java) }
    private val mNotesAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initToolbar()
        initAddButton()
        initList()
    }

    private fun initToolbar() {
        supportActionBar?.title = getString(R.string.list_title)
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initList() {
        recyclerView.adapter = mNotesAdapter

        mListViewModel.notes.observe(this, Observer { list ->
            if (list.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                mNotesAdapter.update(list)
            }
        })
    }

}
