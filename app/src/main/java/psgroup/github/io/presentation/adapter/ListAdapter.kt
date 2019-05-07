package psgroup.github.io.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import psgroup.github.io.R
import psgroup.github.io.domain.DomainNote

class ListAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var mNoteToDisplay: List<DomainNote> = listOf()

    fun update(notes: List<DomainNote>) {
        mNoteToDisplay = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        // TODO: Реализовать обновление View через ViewHolder
    }

    override fun getItemCount(): Int = mNoteToDisplay.count()

}