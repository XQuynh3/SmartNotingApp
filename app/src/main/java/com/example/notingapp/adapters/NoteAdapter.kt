package com.example.notingapp.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.notingapp.databinding.ItemNoteBinding
import com.example.notingapp.model.Note
import com.example.notingapp.repository.NoteRepository
import com.example.notingapp.ui.CreateNoteActivity
import java.text.DateFormat
import kotlin.collections.ArrayList

import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes = ArrayList<Note>()

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

        // 🔥 HIỂN THỊ LOCATION
        try {
            if (note.locationName != null) {
                holder.binding.locationText.text = "📍 ${note.locationName}"
                holder.binding.locationText.visibility = android.view.View.VISIBLE
            } else {
                holder.binding.locationText.visibility = android.view.View.GONE
            }
        } catch (_: Exception) {}

        // 👉 CLICK mở edit (GIỮ NGUYÊN)
        holder.binding.contentLayout.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CreateNoteActivity::class.java)
            intent.putExtra("noteId", note.id)
            context.startActivity(intent)
        }

        // 🔥 LONG PRESS DELETE (GIỮ NGUYÊN)
        holder.binding.contentLayout.setOnLongClickListener {

            val context = holder.itemView.context

            holder.itemView.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)

            AlertDialog.Builder(context)
                .setTitle("Delete this note?")
                .setMessage("This action cannot be undone.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete") { _, _ ->

                    val client = LocationServices.getGeofencingClient(context)
                    client.removeGeofences(listOf(note.id.toString()))

                    onDeleteClick?.invoke(note)
                }
                .setNegativeButton("Cancel", null)
                .show()

            true
        }

        // ================================
        // 🔥🔥🔥 NEW: SHARE BUTTON
        // ================================
        holder.binding.shareBtn.setOnClickListener {

            val context = holder.itemView.context

            val input = EditText(context)
            input.hint = "Enter userId (e.g. userB)"

            AlertDialog.Builder(context)
                .setTitle("Share Note")
                .setView(input)
                .setPositiveButton("Send") { _, _ ->

                    val targetUser = input.text.toString()

                    if (targetUser.isEmpty()) return@setPositiveButton

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val repo = NoteRepository(context)
                            repo.sendShareRequest(
                                noteId = note.id.toString(),
                                targetUserId = targetUser
                            )
                        } catch (_: Exception) {}
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}