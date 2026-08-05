import { useState } from "react";

function App() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [users, setUsers] = useState([]);

  // INSERT INTO DATABASE
  function handleLogin() {
    fetch("http://127.0.0.1:8000/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    })
      .then((res) => res.json())
      .then((data) => {
        alert(data.message);

        setUsername("");
        setPassword("");
      });
  }

  // GET DATA FROM DATABASE
  function showUsers() {
    fetch("http://127.0.0.1:8000/users")
      .then((res) => res.json())
      .then((data) => {
        setUsers(data);
      });
  }

  //update user in database
  function updateUser(id) {
    const newUsername = prompt("Enter new username:");
    const newPassword = prompt("Enter new password:");

    if (!newUsername || !newPassword) return;

    fetch(`http://127.0.0.1:8000/users/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: newUsername,
        password: newPassword,
      }),
    })
      .then((res) => res.json())
      .then((data) => {
        alert(data.message);
        showUsers(); // refresh list
      });
  }

  //delete user from database

  function deleteUser(id) {
    const confirmDelete = window.confirm("Delete this user?");

    if (!confirmDelete) return;

    fetch(`http://127.0.0.1:8000/users/${id}`, {
      method: "DELETE",
    })
      .then((res) => res.json())
      .then((data) => {
        alert(data.message);
        showUsers(); // refresh list
      });
  }

  return (
    <div
      style={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
        fontFamily: "'Segoe UI', sans-serif",
        padding: "20px",
      }}
    >
      <div
        style={{
          width: "380px",
          background: "rgba(255,255,255,0.15)",
          backdropFilter: "blur(15px)",
          WebkitBackdropFilter: "blur(15px)",
          border: "1px solid rgba(255,255,255,0.25)",
          borderRadius: "20px",
          padding: "35px",
          boxShadow: "0 20px 40px rgba(0,0,0,0.25)",
        }}
      >
        <div style={{ textAlign: "center", marginBottom: "25px" }}>
          <div style={{ fontSize: "55px" }}>🎓</div>

          <h1
            style={{
              color: "white",
              marginBottom: "8px",
            }}
          >
            Student Portal
          </h1>

          <p
            style={{
              color: "#e8e8e8",
              fontSize: "14px",
              margin: 0,
            }}
          >
            Welcome back! Please login.
          </p>
        </div>

        <input
          type="text"
          placeholder="👤 Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{
            width: "100%",
            padding: "14px",
            marginBottom: "15px",
            borderRadius: "12px",
            border: "none",
            outline: "none",
            fontSize: "15px",
            boxSizing: "border-box",
            backgroundColor: "rgba(255,255,255,0.9)",
            color: "#000", // Input text color
          }}
        />

        <input
          type="password"
          placeholder="🔒 Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{
            width: "100%",
            padding: "14px",
            marginBottom: "22px",
            borderRadius: "12px",
            border: "none",
            outline: "none",
            fontSize: "15px",
            boxSizing: "border-box",
            backgroundColor: "rgba(255,255,255,0.9)",
            color: "#000", // Input text color
          }}
        />

        <button
          onClick={handleLogin}
          style={{
            width: "100%",
            padding: "14px",
            border: "none",
            borderRadius: "12px",
            background: "linear-gradient(90deg,#4facfe,#00f2fe)",
            color: "white",
            fontSize: "16px",
            fontWeight: "bold",
            cursor: "pointer",
            marginBottom: "12px",
            boxShadow: "0 10px 20px rgba(0,0,0,0.2)",
          }}
        >
          Login
        </button>

        <button
          onClick={showUsers}
          style={{
            width: "100%",
            padding: "14px",
            border: "none",
            borderRadius: "12px",
            background: "linear-gradient(90deg,#43e97b,#38f9d7)",
            color: "white",
            fontSize: "16px",
            fontWeight: "bold",
            cursor: "pointer",
            boxShadow: "0 10px 20px rgba(0,0,0,0.2)",
          }}
        >
          Show Users
        </button>

        <hr
          style={{
            margin: "30px 0",
            borderColor: "rgba(255,255,255,0.3)",
          }}
        />

        <h2
          style={{
            textAlign: "center",
            color: "white",
            marginBottom: "20px",
          }}
        >
          Registered Users
        </h2>

        {users.length === 0 ? (
          <p
            style={{
              textAlign: "center",
              color: "#f2f2f2",
            }}
          >
            No users available
          </p>
        ) : (
          users.map((user) => (<div
  key={user.id}
  style={{
    background: "rgba(255,255,255,0.9)",
    borderRadius: "12px",
    padding: "15px",
    marginBottom: "12px",
    boxShadow: "0 5px 15px rgba(0,0,0,0.15)",
  }}
>
  <div
    style={{
      fontWeight: "bold",
      color: "#333",
      fontSize: "16px",
    }}
  >
    👤 {user.username}
  </div>

  <div
    style={{
      color: "#666",
      marginTop: "6px",
      fontSize: "14px",
    }}
  >
    🔑 {user.password}
  </div>

  <div
    style={{
      display: "flex",
      gap: "10px",
      marginTop: "15px",
    }}
  >
    <button
      onClick={() => updateUser(user.id)}
      style={{
        flex: 1,
        padding: "10px",
        border: "none",
        borderRadius: "8px",
        background: "#ffc107",
        cursor: "pointer",
        fontWeight: "bold",
      }}
    >
      ✏️ Update
    </button>

    <button
      onClick={() => deleteUser(user.id)}
      style={{
        flex: 1,
        padding: "10px",
        border: "none",
        borderRadius: "8px",
        background: "#dc3545",
        color: "white",
        cursor: "pointer",
        fontWeight: "bold",
      }}
    >
      🗑 Delete
    </button>
  </div>
</div>))
        )}
      </div>
    </div>
  );
}

export default App;
