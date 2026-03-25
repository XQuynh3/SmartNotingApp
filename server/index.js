const express = require('express')
const mongoose = require('mongoose')
const cors = require('cors')

const app = express()

// 🔥 MIDDLEWARE
app.use(cors())
app.use(express.json())

// 🔥 CONNECT MONGODB ATLAS
mongoose.connect(
  "mongodb+srv://xuanquynh3824_db_user:smartnoting@cluster0.r5ldcqr.mongodb.net/notingapp?retryWrites=true&w=majority"
)
.then(() => console.log("✅ MongoDB Connected"))
.catch(err => console.error("❌ MongoDB Error:", err))

// 🔥 SCHEMA
const NoteSchema = new mongoose.Schema({
    id: Number,
    title: String,
    content: String,
    latitude: Number,
    longitude: Number,
    locationName: String
})

const Note = mongoose.model('Note', NoteSchema)


// =========================
// 🚀 API
// =========================

// 🔥 CREATE
app.post('/notes', async (req, res) => {
    try {
        const note = new Note(req.body)
        await note.save()
        res.send(note)
    } catch (err) {
        res.status(500).send(err)
    }
})


// 🔥 GET ALL
app.get('/notes', async (req, res) => {
    try {
        const notes = await Note.find()
        res.send(notes)
    } catch (err) {
        res.status(500).send(err)
    }
})


// 🔥 DELETE
app.delete('/notes/:id', async (req, res) => {
    try {
        await Note.deleteOne({ id: Number(req.params.id) })
        res.send("Deleted")
    } catch (err) {
        res.status(500).send(err)
    }
})


// 🔥 UPDATE (BONUS - nên có)
app.put('/notes/:id', async (req, res) => {
    try {
        const updated = await Note.findOneAndUpdate(
            { id: Number(req.params.id) },
            req.body,
            { new: true }
        )
        res.send(updated)
    } catch (err) {
        res.status(500).send(err)
    }
})


// 🔥 SERVER
app.listen(3000, '0.0.0.0', () => {
    console.log("Server running on http://0.0.0.0:3000")
})