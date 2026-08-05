import { useEffect, useMemo, useState } from 'react';

const STORAGE_KEY = 'task7-simple-notes';

const seedNotes = [
  {
    id: 'note-1',
    title: 'Welcome note',
    content: 'This is a simple React note app with create, edit, delete, search, and local storage.',
    createdAt: new Date().toISOString(),
  },
];

function loadNotes() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) {
      return seedNotes;
    }

    const parsed = JSON.parse(raw);
    return Array.isArray(parsed) && parsed.length ? parsed : seedNotes;
  } catch {
    return seedNotes;
  }
}

function uid() {
  if (typeof crypto !== 'undefined' && crypto.randomUUID) {
    return crypto.randomUUID();
  }

  return `note-${Date.now()}-${Math.random().toString(16).slice(2)}`;
}

function App() {
  const [notes, setNotes] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [search, setSearch] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const selectedNote = useMemo(
    () => notes.find((note) => note.id === selectedId) ?? null,
    [notes, selectedId],
  );

  useEffect(() => {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(notes));
    } catch {
      // Ignore storage errors.
    }
  }, [notes]);

  useEffect(() => {
    // Try to load from server first, fall back to localStorage
    fetch('/api/notes')
      .then((r) => {
        if (!r.ok) throw new Error('no api');
        return r.json();
      })
      .then((data) => {
        setNotes(data.map((n) => ({ ...n })));
        setSelectedId(data[0]?.id ?? null);
      })
      .catch(() => {
        const local = loadNotes();
        setNotes(local);
        setSelectedId(local[0]?.id ?? null);
      });
  }, []);

  useEffect(() => {
    if (selectedNote) {
      setTitle(selectedNote.title);
      setContent(selectedNote.content);
      return;
    }

    setTitle('');
    setContent('');
  }, [selectedNote]);

  const visibleNotes = useMemo(() => {
    const term = search.trim().toLowerCase();

    if (!term) {
      return notes;
    }

    return notes.filter(
      (note) =>
        note.title.toLowerCase().includes(term) || note.content.toLowerCase().includes(term),
    );
  }, [notes, search]);

  function handleSubmit(event) {
    event.preventDefault();

    const nextNote = {
      id: selectedNote ? selectedNote.id : uid(),
      title: title.trim() || 'Untitled note',
      content: content.trim(),
      createdAt: selectedNote ? selectedNote.createdAt : new Date().toISOString(),
      updatedAt: new Date().toISOString(),
    };

    if (selectedNote) {
      // try server update, fallback to local
      fetch(`/api/notes/${selectedNote.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(nextNote),
      })
        .then((r) => {
          if (!r.ok) throw new Error('no api');
          setNotes((current) => current.map((note) => (note.id === nextNote.id ? nextNote : note)));
        })
        .catch(() => {
          setNotes((current) => current.map((note) => (note.id === nextNote.id ? nextNote : note)));
        });

      return;
    }

    // create new note
    fetch('/api/notes', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(nextNote),
    })
      .then((r) => {
        if (!r.ok) throw new Error('no api');
        return r.json();
      })
      .then((json) => {
        nextNote.id = json.id || nextNote.id;
        setNotes((current) => [nextNote, ...current]);
        setSelectedId(nextNote.id);
      })
      .catch(() => {
        setNotes((current) => [nextNote, ...current]);
        setSelectedId(nextNote.id);
      });
  }

  function handleSelect(note) {
    setSelectedId(note.id);
  }

  function handleNew() {
    setSelectedId(null);
    setTitle('');
    setContent('');
  }

  function handleDelete(noteId) {
    // try server delete first
    fetch(`/api/notes/${noteId}`, { method: 'DELETE' })
      .then((r) => {
        if (!r.ok) throw new Error('no api');
        setNotes((current) => current.filter((note) => note.id !== noteId));
        if (selectedId === noteId) setSelectedId(null);
      })
      .catch(() => {
        setNotes((current) => current.filter((note) => note.id !== noteId));
        if (selectedId === noteId) setSelectedId(null);
      });
  }

  return (
    <div className="app-shell">
      <main className="app-card">
        <header className="header">
          <div>
            <p className="eyebrow">Task 7</p>
            <h1>Simple Note App</h1>
            <p className="subtitle">A clean React CRUD app with only the essentials.</p>
          </div>

          <button type="button" className="primary-btn" onClick={handleNew}>
            New Note
          </button>
        </header>

        <section className="controls">
          <input
            type="text"
            className="input"
            placeholder="Search notes..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />
        </section>

        <section className="layout">
          <aside className="notes-list">
            {visibleNotes.length ? (
              visibleNotes.map((note) => (
                <button
                  key={note.id}
                  type="button"
                  className={`note-item ${selectedId === note.id ? 'active' : ''}`}
                  onClick={() => handleSelect(note)}
                >
                  <strong>{note.title}</strong>
                  <span>{note.content.slice(0, 70) || 'No content yet.'}</span>
                  <small>{new Date(note.createdAt).toLocaleDateString()}</small>
                </button>
              ))
            ) : (
              <div className="empty-state">
                <h2>No notes found</h2>
                <p>Create a new note or clear the search text.</p>
              </div>
            )}
          </aside>

          <section className="editor">
            <form onSubmit={handleSubmit} className="form">
              <label>
                Title
                <input
                  type="text"
                  className="input"
                  value={title}
                  onChange={(event) => setTitle(event.target.value)}
                  placeholder="Note title"
                />
              </label>

              <label>
                Content
                <textarea
                  className="textarea"
                  rows="10"
                  value={content}
                  onChange={(event) => setContent(event.target.value)}
                  placeholder="Write your note here..."
                />
              </label>

              <div className="form-actions">
                <button type="button" className="secondary-btn" onClick={handleNew}>
                  Clear
                </button>
                <button type="submit" className="primary-btn">
                  {selectedNote ? 'Update Note' : 'Save Note'}
                </button>
              </div>
            </form>

            <div className="preview">
              <p className="eyebrow">Preview</p>
              <h2>{title.trim() || 'Untitled note'}</h2>
              <p>{content.trim() || 'Your note preview will appear here.'}</p>
            </div>

            {selectedNote && (
              <button
                type="button"
                className="danger-btn"
                onClick={() => handleDelete(selectedNote.id)}
              >
                Delete Selected Note
              </button>
            )}
          </section>
        </section>
      </main>
    </div>
  );
}

export default App;