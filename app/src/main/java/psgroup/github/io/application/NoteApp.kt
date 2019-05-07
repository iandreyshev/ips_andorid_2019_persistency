package psgroup.github.io.application

import android.app.Application
import android.content.Intent
import io.reactivex.Observable
import psgroup.github.io.domain.DomainNote
import psgroup.github.io.presentation.activity.EditorActivity
import psgroup.github.io.storage.IStorage
import psgroup.github.io.storage.Note
import psgroup.github.io.storage.memory.MemoryStorage
import psgroup.github.io.viewModel.EditorViewModel

class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        storage = MemoryStorage()
    }

    fun list(): List<DomainNote> = storage.list().toDomainTasks()

    fun listObservable(): Observable<List<DomainNote>> {
        return storage.listObservable()
            .map { it.toDomainTasks() }
    }

    private fun List<Note>.toDomainTasks() = map { task ->
        DomainNote(task.title, task.description, task.creationDateInMillis, object : DomainNote.IListener {
            override fun onOpen() {
                val intent = Intent(this@NoteApp, EditorActivity::class.java)
                intent.putExtra(EditorViewModel.KEY_ID, task.id)
                intent.putExtra(EditorViewModel.KEY_TITLE, task.title)
                intent.putExtra(EditorViewModel.KEY_DESCRIPTION, task.description)
                startActivity(intent)
            }
        })
    }

    companion object {
        lateinit var instance: NoteApp
        lateinit var storage: IStorage
            private set
    }

}
