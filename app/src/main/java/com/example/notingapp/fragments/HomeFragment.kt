package com.example.notingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notingapp.MainActivity
import com.example.notingapp.adapter.NoteAdapter
import com.example.notingapp.data.AppDatabase
import com.example.notingapp.databinding.FragmentHomeBinding
import com.example.notingapp.model.Note
import com.example.notingapp.ui.CreateNoteActivity
import com.example.notingapp.viewmodel.NoteViewModel
import com.example.notingapp.viewmodel.TagViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NoteViewModel by viewModels()
    private val tagViewModel: TagViewModel by viewModels()

    private lateinit var adapter: NoteAdapter

    private var currentTag = "All"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupTagFilter()
        setupSearch()
        observeNotes()

        binding.addNoteBtn.setOnClickListener {
            startActivity(Intent(requireContext(), CreateNoteActivity::class.java))
        }
    }

    private fun setupRecycler() {

        adapter = NoteAdapter()

        // 🔥 CONNECT EVENT
        adapter.onDeleteClick = { note ->
            deleteNote(note)
        }

        binding.noteRecycler.layoutManager =
            LinearLayoutManager(requireContext())

        binding.noteRecycler.adapter = adapter
    }

    // 🔥🔥🔥 CHUẨN PRO LOGIC
    private fun deleteNote(note: Note) {

        val client = LocationServices.getGeofencingClient(requireContext())

        // 🔥 REMOVE GEOFENCE
        client.removeGeofences(listOf(note.id.toString()))

        // 🔥 DELETE DB
        lifecycleScope.launch(Dispatchers.IO) {
            AppDatabase.getDatabase(requireContext())
                .noteDao()
                .delete(note)
        }
    }

    private fun observeNotes() {

        viewModel.notes.observe(viewLifecycleOwner) { notes ->

            adapter.submitList(notes)

            (activity as? MainActivity)?.let {
                it.findViewById<View>(com.example.notingapp.R.id.quoteLayout)?.visibility =
                    if (notes.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupSearch() {

        binding.searchInput.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val query = s.toString()

                if (query.isEmpty()) {
                    applyFilter()
                } else {
                    viewModel.search(query).observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupTagFilter() {

        tagViewModel.tags.observe(viewLifecycleOwner) { tagList ->

            val tagNames = mutableListOf("All")
            tagNames.addAll(tagList.map { it.name })

            val adapterSpinner = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tagNames
            )

            binding.tagFilter.adapter = adapterSpinner

            binding.tagFilter.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        currentTag = tagNames[position]
                        applyFilter()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
    }

    private fun applyFilter() {

        if (currentTag == "All") {

            viewModel.notes.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

        } else {

            viewModel.filterTag(currentTag)
                .observe(viewLifecycleOwner) {

                    adapter.submitList(it)
                }
        }
    }
}