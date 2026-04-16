const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

mongoose.connect("mongodb+srv://xuanquynh3824_db_user:smartnoting@cluster0.r5ldcqr.mongodb.net/?appName=Cluster0")
.then(() => console.log("MongoDB connected successfully!"))
.catch(err => console.error("MongoDB connection error:", err));

/* ===================== NOTE ===================== */
const NoteSchema = new mongoose.Schema({
    title: String,
    content: String,

    ownerId: String,
    sharedWith: [String],

    updatedBy: String,
    updatedAt: Number,

    latitude: Number,
    longitude: Number
});

const Note = mongoose.model("Note", NoteSchema);

/* ===================== SHARE REQUEST ===================== */
const ShareRequestSchema = new mongoose.Schema({
    noteId: String,
    fromUserId: String,
    toUserId: String,
    status: { type: String, default: "pending" } // pending | accepted | rejected
});

const ShareRequest = mongoose.model("ShareRequest", ShareRequestSchema);

/* ===================== APIs ===================== */

// GET NOTES
app.get("/notes", async (req, res) => {
    const userId = req.query.userId;

    const notes = await Note.find({
        $or: [
            { ownerId: userId },
            { sharedWith: userId }
        ]
    });

    res.json(notes);
});

// CREATE NOTE
app.post("/notes", async (req, res) => {
    const note = new Note({
        ...req.body,
        updatedAt: Date.now()
    });

    await note.save();
    res.json(note);
});

// UPDATE NOTE
app.put("/notes/:id", async (req, res) => {
    const note = await Note.findByIdAndUpdate(
        req.params.id,
        { ...req.body, updatedAt: Date.now() },
        { new: true }
    );

    res.json(note);
});

// 🔥 CREATE SHARE REQUEST
app.post("/share-request", async (req, res) => {
    const { noteId, fromUserId, toUserId } = req.body;

    const request = new ShareRequest({
        noteId,
        fromUserId,
        toUserId
    });

    await request.save();

    res.json({ message: "Request sent" });
});

// 🔥 GET REQUESTS (cho user nhận)
app.get("/share-request", async (req, res) => {
    const userId = req.query.userId;

    const requests = await ShareRequest.find({
        toUserId: userId,
        status: "pending"
    });

    res.json(requests);
});

// 🔥 ACCEPT REQUEST
app.post("/share-request/accept", async (req, res) => {
    const { requestId } = req.body;

    const request = await ShareRequest.findById(requestId);

    await Note.findByIdAndUpdate(request.noteId, {
        $addToSet: { sharedWith: request.toUserId }
    });

    request.status = "accepted";
    await request.save();

    res.json({ message: "Accepted" });
});

// 🔥 REJECT REQUEST
app.post("/share-request/reject", async (req, res) => {
    const { requestId } = req.body;

    await ShareRequest.findByIdAndUpdate(requestId, {
        status: "rejected"
    });

    res.json({ message: "Rejected" });
});

app.listen(3000,"0.0.0.0", () => {
    console.log("Server running on port 3000");
});