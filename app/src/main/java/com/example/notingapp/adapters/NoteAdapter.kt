package com.example.notingapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notingapp.databinding.ItemNoteBinding
import com.example.notingapp.model.Note
import com.example.notingapp.ui.CreateNoteActivity
import java.text.DateFormat
import kotlin.collections.ArrayList

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes = ArrayList<Note>()

    // 🔥 EVENT CALLBACK
    var onDeleteClick: ((Note) -> Unit)? = null

    fun submitList(list: List<Note>) {
        notes = ArrayList(list)
        notifyDataSetChanged()
    }

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NoteViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = notes[position]

        holder.binding.title.text = note.title
        holder.binding.content.text = note.content
        holder.binding.tag.text = note.tag

        holder.binding.time.text =
            DateFormat.getDateTimeInstance().format(note.lastModified)

        val contentView = holder.binding.contentLayout
        val deleteLayout = holder.binding.deleteLayout

        var startX = 0f
        var startTranslation = 0f

        contentView.setOnTouchListener { v, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    startX = event.rawX
                    startTranslation = v.translationX
                }

                MotionEvent.ACTION_MOVE -> {
                    val dx = event.rawX - startX
                    val newX = startTranslation + dx

                    if (newX <= 0 && newX >= -250) {
                        v.translationX = newX
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (v.translationX < -150) {
                        v.animate().translationX(-250f).setDuration(200).start()
                    } else {
                        v.animate().translationX(0f).setDuration(200).start()
                    }
                }
            }
            true
        }

        // 🔥 GỬI EVENT LÊN FRAGMENT
        deleteLayout.setOnClickListener {
            onDeleteClick?.invoke(note)
        }

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                CreateNoteActivity::class.java
            )

            intent.putExtra("noteId", note.id)
            holder.itemView.context.startActivity(intent)
        }
    }
}