# Task7 - Simple Note App (with MySQL backend)

This project is a React frontend (Vite) with an optional Express backend that persists notes to MySQL.

Setup

1. Install dependencies:

```powershell
cd "d:\Placement Training\FULL STACK Placement\Assignment\Task7"
npm install
```

2. Configure MySQL connection: copy `server/.env.example` to `server/.env` and update the values.

3. Run the DB migration SQL to create the `notedb` and `notes` table (replace credentials as needed):

```sql
-- from a MySQL client
SOURCE server/migrations/init.sql;
```

Or from shell (adjust `-u` and `-p`):

```powershell
mysql -u root -p < server/migrations/init.sql
```

4. Start the backend server (development):

```powershell
npm run dev:server
```

5. Start the frontend dev server (in another terminal):

```powershell
npm run dev
```

Notes

- The frontend tries to use the API at `/api/notes`. If the backend is not running, it falls back to `localStorage`.
- To serve the built frontend from Express, run `npm run build` then `npm run server`.
