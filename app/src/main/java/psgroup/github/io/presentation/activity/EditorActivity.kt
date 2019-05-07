package psgroup.github.io.presentation.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_editor.*
import psgroup.github.io.R
import psgroup.github.io.viewModel.EditorViewModel

class EditorActivity : AppCompatActivity() {

    private val mViewModel by lazy { ViewModelProviders.of(this).get(EditorViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        initNoteToEdit()
        initToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (mViewModel.isCreateNewNote) {
            menuInflater.inflate(R.menu.menu_create, menu)
        } else {
            menuInflater.inflate(R.menu.menu_edit, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.save -> {
                handleSave()
                return true
            }
            R.id.delete -> {
                handleDelete()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (mViewModel.isCreateNewNote) {
            getString(R.string.constructor_title)
        } else {
            getString(R.string.editor_title)
        }
    }

    private fun initNoteToEdit() {
        mViewModel.initNoteToEdit(intent)

        titleView.setText(mViewModel.title)
        titleView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.title = s.toString()
            }
        })

        descriptionView.setText(mViewModel.description)
        descriptionView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.description = s.toString()
            }
        })
    }

    private fun handleSave() {
        mViewModel.save()
        finish()
    }

    private fun handleDelete() {
        mViewModel.delete()
        finish()
    }

}
