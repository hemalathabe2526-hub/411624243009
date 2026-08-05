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

  return (
  <div
    style={{
      minHeight: "100vh",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      backgroundColor: "#f4f6f9",
      fontFamily: "Arial",
    }}
  >
    <div
      style={{
        width: "350px",
        backgroundColor: "white",
        padding: "25px",
        borderRadius: "10px",
        boxShadow: "0 4px 10px rgba(0,0,0,0.2)",
      }}
    >
      <h1 style={{ textAlign: "center", color: "#333" }}>
        Student Login
      </h1>

      <input
        type="text"
        placeholder="Enter Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        style={{
          width: "100%",
          padding: "10px",
          marginBottom: "15px",
          border: "1px solid #ccc",
          borderRadius: "5px",
          boxSizing: "border-box",
        }}
      />

      <input
        type="password"
        placeholder="Enter Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={{
          width: "100%",
          padding: "10px",
          marginBottom: "20px",
          border: "1px solid #ccc",
          borderRadius: "5px",
          boxSizing: "border-box",
        }}
      />

      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          gap: "10px",
        }}
      >
        <button
          onClick={handleLogin}
          style={{
            flex: 1,
            padding: "10px",
            backgroundColor: "#4CAF50",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontWeight: "bold",
          }}
        >
          Login
        </button>

        <button
          onClick={showUsers}
          style={{
            flex: 1,
            padding: "10px",
            backgroundColor: "#2196F3",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontWeight: "bold",
          }}
        >
          Show Users
        </button>
      </div>

      <hr style={{ margin: "25px 0" }} />

      <h2 style={{ textAlign: "center", color: "#444" }}>
        Users
      </h2>

      {users.length === 0 ? (
        <p style={{ textAlign: "center", color: "gray" }}>
          No users to display
        </p>
      ) : (
        users.map((user) => (
          <div
            key={user.id}
            style={{
              backgroundColor: "#f8f9fa",
              padding: "10px",
              borderRadius: "5px",
              marginBottom: "10px",
              borderLeft: "4px solid #2196F3",
            }}
          >
            <strong>Name:</strong> {user.username}
            <br />
            <strong>Password:</strong> {user.password}
          </div>
        ))
      )}
    </div>
  </div>
);
}

export default App;