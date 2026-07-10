# 🧪 Postman Testing Guide - JSONPlaceholder API

This guide provides instructions for testing all HTTP methods (GET, POST, PUT, PATCH, DELETE) with Postman.

---

## 📋 Prerequisites

- Download and install [Postman](https://www.postman.com/downloads/)
- Base URL: `https://jsonplaceholder.typicode.com`
- JSONPlaceholder is a free online REST API that provides fake data

---

## 🔍 Base Configuration

**Base URL:** `https://jsonplaceholder.typicode.com`

**Content-Type Header:** Add this to all requests
- Key: `Content-Type`
- Value: `application/json`

---

## 📌 1. GET - Fetch All Users

### Request Details
- **Method:** GET
- **URL:** `https://jsonplaceholder.typicode.com/users`
- **Headers:** None required
- **Body:** None

### Steps in Postman
1. Create a new request
2. Select method as **GET**
3. Enter URL: `https://jsonplaceholder.typicode.com/users`
4. Click **Send**

### Expected Response
```json
[
  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {...},
    "phone": "1-770-736-8031",
    "website": "hildegard.org",
    "company": {...}
  },
  ...
]
```

---

## 🔍 2. GET - Fetch Single User

### Request Details
- **Method:** GET
- **URL:** `https://jsonplaceholder.typicode.com/users/1`
- **Headers:** None required
- **Body:** None

### Steps in Postman
1. Create a new request
2. Select method as **GET**
3. Enter URL: `https://jsonplaceholder.typicode.com/users/1`
4. Click **Send**

### Expected Response
```json
{
  "id": 1,
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  "phone": "1-770-736-8031",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net",
    "bs": "harness real-time e-markets"
  }
}
```

---

## ✏️ 3. POST - Create New User

### Request Details
- **Method:** POST
- **URL:** `https://jsonplaceholder.typicode.com/users`
- **Headers:** 
  - `Content-Type: application/json`
- **Body:** (Raw JSON)

### Steps in Postman
1. Create a new request
2. Select method as **POST**
3. Enter URL: `https://jsonplaceholder.typicode.com/users`
4. Go to **Headers** tab and add:
   - Key: `Content-Type`
   - Value: `application/json`
5. Go to **Body** tab, select **raw** and paste:

```json
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "johndoe@example.com",
  "phone": "123-456-7890",
  "website": "johndoe.com",
  "company": {
    "name": "Tech Solutions",
    "catchPhrase": "Innovative tech company"
  }
}
```

6. Click **Send**

### Expected Response
```json
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "johndoe@example.com",
  "phone": "123-456-7890",
  "website": "johndoe.com",
  "company": {
    "name": "Tech Solutions",
    "catchPhrase": "Innovative tech company"
  },
  "id": 11
}
```

**Note:** JSONPlaceholder simulates the creation and returns the data with an ID, but doesn't actually store it.

---

## 🔄 4. PUT - Update Entire User

### Request Details
- **Method:** PUT
- **URL:** `https://jsonplaceholder.typicode.com/users/1`
- **Headers:** 
  - `Content-Type: application/json`
- **Body:** (Raw JSON - must include all fields)

### Steps in Postman
1. Create a new request
2. Select method as **PUT**
3. Enter URL: `https://jsonplaceholder.typicode.com/users/1`
4. Go to **Headers** tab and add:
   - Key: `Content-Type`
   - Value: `application/json`
5. Go to **Body** tab, select **raw** and paste:

```json
{
  "id": 1,
  "name": "Updated Leanne Graham",
  "username": "UpdatedBret",
  "email": "updated@april.biz",
  "address": {
    "street": "Kulas Light",
    "suite": "Apt. 556",
    "city": "Gwenborough",
    "zipcode": "92998-3874"
  },
  "phone": "1-770-736-8031",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net"
  }
}
```

6. Click **Send**

### Expected Response
```json
{
  "id": 1,
  "name": "Updated Leanne Graham",
  "username": "UpdatedBret",
  "email": "updated@april.biz",
  "address": {...},
  "phone": "1-770-736-8031",
  "website": "hildegard.org",
  "company": {...}
}
```

---

## 🔧 5. PATCH - Partially Update User

### Request Details
- **Method:** PATCH
- **URL:** `https://jsonplaceholder.typicode.com/users/1`
- **Headers:** 
  - `Content-Type: application/json`
- **Body:** (Raw JSON - only fields to update)

### Steps in Postman
1. Create a new request
2. Select method as **PATCH**
3. Enter URL: `https://jsonplaceholder.typicode.com/users/1`
4. Go to **Headers** tab and add:
   - Key: `Content-Type`
   - Value: `application/json`
5. Go to **Body** tab, select **raw** and paste:

```json
{
  "name": "Partially Updated Name",
  "email": "newemail@april.biz"
}
```

6. Click **Send**

### Expected Response
```json
{
  "id": 1,
  "name": "Partially Updated Name",
  "username": "Bret",
  "email": "newemail@april.biz",
  "address": {...},
  "phone": "1-770-736-8031",
  "website": "hildegard.org",
  "company": {...}
}
```

**Note:** PATCH only updates the specified fields, keeping others unchanged.

---

## 🗑️ 6. DELETE - Delete User

### Request Details
- **Method:** DELETE
- **URL:** `https://jsonplaceholder.typicode.com/users/1`
- **Headers:** None required
- **Body:** None

### Steps in Postman
1. Create a new request
2. Select method as **DELETE**
3. Enter URL: `https://jsonplaceholder.typicode.com/users/1`
4. Click **Send**

### Expected Response
```json
{}
```

**Status Code:** 200 OK

**Note:** JSONPlaceholder simulates deletion. The user won't actually be deleted if you fetch again.

---

## 📊 Comparison: PUT vs PATCH

| Feature | PUT | PATCH |
|---------|-----|-------|
| Purpose | Replace entire resource | Partial update |
| Required Fields | All fields must be included | Only changed fields needed |
| Example | Update all user data | Update only name and email |
| URL | `/users/1` | `/users/1` |

### PUT Example
```json
{
  "id": 1,
  "name": "New Name",
  "email": "new@example.com",
  "phone": "999-999-9999",
  "website": "example.com"
}
```

### PATCH Example
```json
{
  "name": "New Name"
}
```

---

## 🔐 HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 204 | No Content - Success with no response body |
| 400 | Bad Request - Invalid data |
| 404 | Not Found - Resource doesn't exist |
| 500 | Internal Server Error |

---

## 💡 Tips & Tricks

1. **Environment Variables:** Save the base URL as a variable
   - Create a variable: `{{base_url}}`
   - Use in requests: `{{base_url}}/users`

2. **Collections:** Save all requests in a collection for easy access

3. **Pre-request Scripts:** Automatically add timestamps or authentication

4. **Tests:** Write tests to validate responses automatically

5. **Mock Data:** Use Postman's mock server to test with fake data

---

## 🚀 Creating a Postman Collection

### Step-by-step:
1. Click **New** → **Collection**
2. Name: "JSONPlaceholder API"
3. Add all requests to this collection
4. Save your collection for future use
5. Share with team members

---

## ✅ Quick Checklist

- [ ] Postman installed
- [ ] Base URL bookmarked: `https://jsonplaceholder.typicode.com`
- [ ] Created GET request for all users
- [ ] Tested GET single user
- [ ] Created POST request with sample data
- [ ] Tested PUT to update user
- [ ] Tested PATCH to partial update
- [ ] Tested DELETE to remove user
- [ ] All responses verified
- [ ] Collection saved

---

## 📚 Additional Resources

- [JSONPlaceholder Documentation](https://jsonplaceholder.typicode.com/)
- [Postman Learning Center](https://learning.postman.com/)
- [REST API Best Practices](https://restfulapi.net/)

---

**Happy Testing! 🎉**
