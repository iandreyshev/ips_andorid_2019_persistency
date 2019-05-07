package psgroup.github.io.storage

import io.reactivex.Observable

interface IStorage {
    fun add(note: Note): Note
    fun update(id: Long, note: Note)
    fun delete(id: Long)
    fun list(): List<Note>
    fun listObservable(): Observable<List<Note>>
}
