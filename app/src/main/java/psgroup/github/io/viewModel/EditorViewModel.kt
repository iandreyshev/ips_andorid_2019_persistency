package psgroup.github.io.viewModel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import psgroup.github.io.application.NoteApp
import psgroup.github.io.storage.Note

class EditorViewModel : ViewModel() {

    var isCreateNewNote: Boolean = false
        private set

    var title: String? = ""
    var description: String? = ""

    private var mNoteId: Long? = null

    fun initNoteToEdit(intent: Intent) {
        if (intent.extras?.getBoolean(KEY_INIT_COMPLETED) == true) {
            return
        }

        mNoteId = intent.extras?.getLong(KEY_ID)
        title = intent.extras?.getString(KEY_TITLE)
        description = intent.extras?.getString(KEY_DESCRIPTION)

        isCreateNewNote = mNoteId == null || title == null || description == null

        intent.putExtra(KEY_INIT_COMPLETED, true)
    }

    fun save() {
        title ?: return
        description ?: return
        val note = Note(
            id = mNoteId ?: 0,
            title = title ?: "",
            description = description ?: "",
            creationDateInMillis = System.currentTimeMillis()
        )

        if (isCreateNewNote) NoteApp.storage.add(note)
        else NoteApp.storage.update(note.id, note)
    }

    fun delete() {
        mNoteId ?: return
        NoteApp.storage.delete(mNoteId ?: 0)
    }

    companion object {
        const val KEY_ID = "key.id"
        const val KEY_TITLE = "key.title"
        const val KEY_DESCRIPTION = "key.description"

        private const val KEY_INIT_COMPLETED = "key.init.completed"
    }

}
