package com.example.notingapp.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.notingapp.R
import com.example.notingapp.model.Note
import com.example.notingapp.model.Tag
import com.example.notingapp.viewmodel.TagViewModel
import com.example.notingapp.viewmodel.NoteViewModel
import com.example.notingapp.location.GeofenceHelper
import com.google.android.gms.location.LocationServices

class CreateNoteActivity : AppCompatActivity() {

    lateinit var titleInput: EditText
    lateinit var contentInput: EditText
    lateinit var tagSpinner: Spinner
    lateinit var boldBtn: Button
    lateinit var italicBtn: Button
    lateinit var textSizeSeek: SeekBar
    lateinit var saveBtn: Button
    lateinit var addTagBtn: Button
    lateinit var locationBtn: Button

    private val tagViewModel: TagViewModel by viewModels()
    private val noteViewModel: NoteViewModel by viewModels()

    private var tagList: List<Tag> = emptyList()

    var selectedTag = ""
    var selectedPosition = 0
    var textSize = 16f

    private var noteId = -1

    var latitude: Double? = null
    var longitude: Double? = null
    var locationName: String? = null

    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        titleInput = findViewById(R.id.titleInput)
        contentInput = findViewById(R.id.contentInput)
        tagSpinner = findViewById(R.id.tagSpinner)
        boldBtn = findViewById(R.id.boldBtn)
        italicBtn = findViewById(R.id.italicBtn)
        textSizeSeek = findViewById(R.id.textSizeSeek)
        saveBtn = findViewById(R.id.saveBtn)
        addTagBtn = findViewById(R.id.addTagBtn)
        locationBtn = findViewById(R.id.locationBtn)

        noteId = intent.getIntExtra("noteId", -1)

        setupTagSpinner()
        setupTextSize()
        setupStyleButtons()

        locationBtn.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivityForResult(intent, 100)
        }

        addTagBtn.setOnClickListener { showAddTagDialog() }

        tagSpinner.setOnLongClickListener {
            deleteCurrentTag()
            true
        }

        if (noteId != -1) loadNote()

        saveBtn.setOnClickListener { saveNote() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {

            latitude = data?.getDoubleExtra("lat", 0.0)
            longitude = data?.getDoubleExtra("lng", 0.0)
            locationName = data?.getStringExtra("locationName")

            Toast.makeText(this, locationName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTagSpinner() {
        tagViewModel.tags.observe(this) { tags ->
            tagList = tags
            val tagNames = tags.map { it.name }

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                tagNames
            )

            tagSpinner.adapter = adapter

            tagSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                        selectedPosition = position
                        selectedTag = tagNames[position]
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

            currentNote?.let { note ->
                val index = tagNames.indexOf(note.tag)
                if (index >= 0) {
                    tagSpinner.setSelection(index)
                }
            }
        }
    }

    private fun setupTextSize() {
        textSizeSeek.max = 40
        textSizeSeek.progress = 16

        textSizeSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    textSize = progress.toFloat()
                    contentInput.textSize = textSize
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )
    }

    private fun setupStyleButtons() {
        boldBtn.setOnClickListener { contentInput.setTypeface(null, Typeface.BOLD) }
        italicBtn.setOnClickListener { contentInput.setTypeface(null, Typeface.ITALIC) }
    }

    private fun loadNote() {
        noteViewModel.notes.observe(this) { notes ->
            val note = notes.find { it.id == noteId } ?: return@observe

            currentNote = note

            titleInput.setText(note.title)
            contentInput.setText(note.content)

            textSize = note.textSize
            contentInput.textSize = textSize
            textSizeSeek.progress = textSize.toInt()

            latitude = note.latitude
            longitude = note.longitude
            locationName = note.locationName
        }
    }

    private fun showAddTagDialog() {
        val input = EditText(this)

        AlertDialog.Builder(this)
            .setTitle("New Tag")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val tagName = input.text.toString()
                if (tagName.isNotEmpty()) {
                    tagViewModel.insert(Tag(name = tagName))
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteCurrentTag() {}

    // 🔥 CHECK PERMISSION
    private fun hasLocationPermission(): Boolean {
        val fine = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val background =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            } else true

        return fine && background
    }

    private fun requestLocationPermission() {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        ActivityCompat.requestPermissions(
            this,
            permissions.toTypedArray(),
            999
        )
    }

    private fun saveNote() {

        val note = Note(
            id = if (noteId == -1) 0 else noteId,
            title = titleInput.text.toString(),
            content = contentInput.text.toString(),
            tag = selectedTag,
            textSize = textSize,
            latitude = latitude,
            longitude = longitude,
            locationName = locationName
        )

        if (noteId == -1) {
            noteViewModel.insert(note)
        } else {
            noteViewModel.update(note)
        }

        if (latitude != null && longitude != null) {

            if (!hasLocationPermission()) {
                requestLocationPermission()
                Toast.makeText(this, "Cần cấp quyền location", Toast.LENGTH_SHORT).show()
                return
            }

            try {
                val geofenceHelper = GeofenceHelper(this)

                val geofence = geofenceHelper.getGeofence(
                    note.id.toString(),
                    latitude!!,
                    longitude!!
                )

                val request = geofenceHelper.getGeofencingRequest(geofence)

                val client = LocationServices.getGeofencingClient(this)

                client.addGeofences(
                    request,
                    geofenceHelper.getPendingIntent()
                )

            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        finish()
    }
}