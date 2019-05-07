package psgroup.github.io.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import psgroup.github.io.application.NoteApp
import psgroup.github.io.domain.DomainNote

class ListViewModel : ViewModel() {

    val notes: LiveData<List<DomainNote>>
        get() = mNotes

    private val mNotes = MutableLiveData<List<DomainNote>>()
    private var mNotesSubscription: Disposable? = null

    init {
        val initialTasks = NoteApp.instance.list()
        mNotes.value = initialTasks

        val notesObservable = NoteApp.instance.listObservable()
        mNotesSubscription = notesObservable.subscribe { notes ->
            mNotes.value = notes
        }
    }

    override fun onCleared() {
        mNotesSubscription?.dispose()
    }

}
