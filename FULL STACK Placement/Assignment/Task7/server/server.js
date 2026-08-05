import express from 'express';
import cors from 'cors';
import { fileURLToPath } from 'url';
import path from 'path';
import dotenv from 'dotenv';
import { createPool } from 'mysql2/promise';

dotenv.config();

const app = express();
app.use(cors());
app.use(express.json());

const PORT = process.env.PORT || 4000;

const pool = createPool({
  host: process.env.DB_HOST || 'localhost',
  user: process.env.DB_USER || 'root',
  password: process.env.DB_PASS || 'Hema_Latha@25726',
  database: process.env.DB_NAME || 'notedb',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0,
});

await pool.query(
  'INSERT INTO notes (id, title, content) VALUES (?, ?, ?)',
  [noteId, title, content]
);

await fetch('http://localhost:4000/api/notes'), {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify({
    title,
    content,
  }),
};

app.get('/api/notes', async (req, res) => {
  try {
    const [rows] = await pool.query('SELECT * FROM notes ORDER BY createdAt DESC');
    res.json(rows);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Failed to fetch notes' });
  }
});

app.post("/api/notes", async (req, res) => {
  console.log(req.body);

  try {
    const { title, content } = req.body;

    const noteId = `note-${Date.now()}`;
    const now = new Date().toISOString().slice(0, 19).replace("T", " ");

    const [result] = await pool.query(
      "INSERT INTO notes (id, title, content, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)",
      [noteId, title, content, now, now]
    );

    console.log(result);

    res.json({ success: true });
  } catch (err) {
    console.error(err);
  }
});

app.put('/api/notes/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const { title, content, updatedAt } = req.body;
    await pool.query('UPDATE notes SET title = ?, content = ?, updatedAt = ? WHERE id = ?', [
      title,
      content,
      updatedAt || new Date().toISOString(),
      id,
    ]);

    res.json({ id });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Failed to update note' });
  }
});

app.delete('/api/notes/:id', async (req, res) => {
  try {
    const { id } = req.params;
    await pool.query('DELETE FROM notes WHERE id = ?', [id]);
    res.json({ id });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Failed to delete note' });
  }
});

// Serve frontend build in production
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const clientDist = path.join(__dirname, '..', 'dist');
app.use(express.static(clientDist));
app.get('*', (req, res) => {
  res.sendFile(path.join(clientDist, 'index.html'));
});

app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
