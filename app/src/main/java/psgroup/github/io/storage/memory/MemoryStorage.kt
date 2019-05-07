package psgroup.github.io.storage.memory

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import psgroup.github.io.storage.IStorage
import psgroup.github.io.storage.Note

class MemoryStorage : IStorage {

    private var mLastId = 0L
    private val mMemory = mutableListOf<Note>()

    private var mPublishSubject = PublishSubject.create<List<Note>>()

    override fun add(note: Note): Note {
        ++mLastId
        val noteToSave = note.copy(id = mLastId)
        mMemory.add(noteToSave)

        mPublishSubject.onNext(mMemory)

        return noteToSave
    }

    override fun update(id: Long, note: Note) {
        val noteToUpdate = mMemory.find { it.id == id } ?: return
        val noteToSave = noteToUpdate.copy(
            title = note.title,
            description = note.description
        )

        mMemory.removeAll { it.id == id }
        mMemory.add(noteToSave)

        mPublishSubject.onNext(mMemory)
    }

    override fun delete(id: Long) {
        mMemory.removeAll { it.id == id }

        mPublishSubject.onNext(mMemory)
    }

    override fun list(): List<Note> = mMemory

    override fun listObservable(): Observable<List<Note>> = mPublishSubject

}
