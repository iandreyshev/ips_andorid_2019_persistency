package psgroup.github.io.storage.objectBox

import io.reactivex.Observable
import psgroup.github.io.storage.IStorage
import psgroup.github.io.storage.Note

class ObjectBoxStorage : IStorage {

    override fun add(note: Note): Note = note

    override fun update(id: Long, note: Note) = Unit

    override fun delete(id: Long) = Unit

    override fun list(): List<Note> = listOf()

    override fun listObservable(): Observable<List<Note>> = Observable.empty()

}
